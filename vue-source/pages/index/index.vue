<template>
	<view class="page">
		<!-- 轮播图 start -->
		<swiper :indicator-dots="true" :autoplay="true" class="carousel">
			<swiper-item v-for="item in carouselList" :key=item.id>
				<image :src="item.image" class="carousel"></image>
			</swiper-item>
		</swiper>
		<!-- 轮播图 end -->

		<!-- 热门超英 start -->
		<view class="page-block hot-area">
			<view class="hot-title-wrapper">
				<image src="../../static/icos/hot.png" class="hot-ico"></image>
				<view class="hot-title">
					热门超英
				</view>
			</view>
			<scroll-view scroll-x="true" class="hot-scrollview">
				<view class="single-poster" v-for="(movie, picIndex) in hotSuperHeroList" :key=movie.id>
					<view class="poster-wrapper">
						<image :src="movie.cover" :data-picIndex="picIndex" @click="lookMe" class="poster"></image>
						<view class="movie-name">{{movie.name}}</view>
						<trailer-stars :innerScore="movie.score" :showNum="true"></trailer-stars>
					</view>
				</view>
			</scroll-view>
		</view>
		<!-- 热门超英 end -->

		<!-- 热门预告片 start -->
		<view class="page-block hot-area">
			<view class="hot-title-wrapper">
				<image src="../../static/icos/108x108.png" class="hot-ico"></image>
				<view class="hot-title">
					热门预告片
				</view>
			</view>
			<view class="hot-trailers">
				<video :id="movie.id" :data-playingIndex="movie.id" @play="iAmPlaying" v-for="movie in hotTrailerList" :key="movie.id"
				 :src="movie.trailer" :poster="movie.poster" controls class="trailer"></video>
			</view>
		</view>
		<!-- 热门预告片 end -->

		<!-- 猜你喜欢 start -->
		<view class="page-block guess-area">
			<view class="hot-title-wrapper">
				<image src="../../static/icos/guess-u-like.png" class="hot-ico"></image>
				<view class="hot-title">
					猜你喜欢
				</view>
			</view>
			<view v-for="(guess,gIndex) in guessULike" :key="guess.id" class="single-guess">
				<navigator :url="'../cover/cover?cover=' + guess.cover">
					<image :src="guess.cover" class="guess-cover-image"></image>
				</navigator>
				<view class="guess-info">
					<view class="guess-title">{{guess.name}}</view>
					<trailer-stars :innerScore="guess.score" :showNum="false"></trailer-stars>
					<view class="guess-desc">
						{{guess.basicInfo}}
					</view>
					<view class="guess-desc">
						{{guess.releaseDate}}
					</view>
				</view>
				<view class="guess-oper" :data-gIndex="gIndex" @click="praiseMe">
					<image src="../../static/icos/praise.png" class="praise-ico"></image>
					<view class="praise-me">
						点赞
					</view>
					<view :animation="animationDataArr[gIndex]" class="praise-me animation-opacity">
						+1
					</view>
				</view>
			</view>
		</view>
		<!-- 猜你喜欢 end -->

	</view>
</template>

<script>
	// import common from "../../common/common.js";
	import trailerStars from "../../components/trailerStars.vue";

	export default {
		data() {
			return {
				carouselList: [],
				hotSuperHeroList: [],
				hotTrailerList: [],
				guessULike: [],
				animationData: {},
				animationDataArr: [{}, {}, {}, {}, {}],
				pics: [] // 放大预览的图片数组
			}
		},
		components: {
			trailerStars
		},
		onUnload() {
			// #ifdef APP-PLUS || MP-WEIXIN
			// 页面卸载的时候,清楚动画数据
			this.animationData = {};
			this.animationDataArr = [{}, {}, {}, {}, {}];
			// #endif
		},
		onPullDownRefresh() {
			this.refresh();
		},
		// #ifdef MP-WEIXIN
		// 页面被隐藏的时候，暂停播放
		onHide() {
			if(this.videoContext) {
				this.videoContext.pause();
			}
		},
		// #endif
		onLoad() {

			// #ifdef APP-PLUS || MP-WEIXIN
			// 在页面创建的时候,创建一个临时动画对象
			this.animation = uni.createAnimation();
			// #endif

			// var serverUrl = common.serverUrl;
			var serverUrl = this.serverUrl;
			// 请求轮播图数据
			uni.request({
				url: serverUrl + '/movieCarousel/list',
				method: 'POST',
				data: {},
				success: res => {
					// 获取真实数据之前,务必判断状态为success
					if (res.data.status === "success") {
						this.carouselList = res.data.data;
					}

				},
				fail: () => {},
				complete: () => {}
			});

			this.refresh();
		},
		methods: {
			iAmPlaying(e) {
				// 播放一个视频的时候,需要暂停其他正在播放的视频
				var trailerId = "";
				if (e) {
					trailerId = e.currentTarget.dataset.playingindex;
					this.videoContext = uni.createVideoContext(trailerId);
				}
				var hotTrailerList = this.hotTrailerList;
				for (var i = 0; i < hotTrailerList.length; i ++) {
					var tempId = hotTrailerList[i].id;
					if (tempId != trailerId) {
						uni.createVideoContext(tempId).pause();
					}
				}
			}
			,
			refresh() {
				uni.showLoading({
					mask: true
				});
				uni.showNavigationBarLoading();

				var serverUrl = this.serverUrl;
				// 请求电影信息
				uni.request({
					url: serverUrl + '/movieInfo/list',
					method: 'POST',
					data: {},
					success: res => {
						// 获取真实数据之前,务必判断状态为success
						if (res.data.status === "success") {
							var retData = res.data.data;
							// 热门超英电影海报信息
							this.hotSuperHeroList = retData;
							// 采集放大预览图片数组
							this.pics = retData.map(x => {
								return x.cover
							});

							// 热门超英电影预告信息
							this.hotTrailerList = retData.slice(8, 10);
							// 猜你喜欢电影信息
							this.guessULike = retData.slice(3, 8);
						}

					},
					fail: () => {},
					complete: () => {
						uni.hideNavigationBarLoading();
						uni.hideLoading();
						uni.stopPullDownRefresh();
					}
				});
			},

			// 实现点击后放大预览效果
			lookMe(e) {
				var picIndex = e.currentTarget.dataset.picindex;
				uni.previewImage({
					current: this.pics[picIndex],
					urls: this.pics
				})
			},

			// 实现点赞动画效果
			praiseMe(e) {
				// #ifdef APP-PLUS || MP-WEIXIN
				// 获取点赞的那行条目的index
				var gIndex = e.currentTarget.dataset.gindex;
				// 构建动画数据,并且通过step来表示这组动画的完成
				this.animation.translateY(-60).opacity(1).step({
					duration: 400
				});
				// 导出动画数据到view组件，实现组件的动画效果
				this.animationData = this.animation;
				this.animationDataArr[gIndex] = this.animationData.export();

				// 还原动画
				setTimeout(function() {
					this.animation.translateY(0).opacity(0).step({
						duration: 0
					});
					this.animationData = this.animation;
					this.animationDataArr[gIndex] = this.animationData.export();
				}.bind(this), 500);
				// #endif
			}
		}
	}
</script>

<style>
	@import url("index.css");
</style>
