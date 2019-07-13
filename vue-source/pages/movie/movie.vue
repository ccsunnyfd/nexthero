<template>
	<view class="page">
		<!-- 视频start -->
		<view class="player">
			<video :src="movieDetail.trailer" :poster="movieDetail.poster" controls class="movie"></video>
		</view>
		<!-- 视频end -->

		<!-- 影片信息start -->
		<view class="movieInfo page-block">
			<image :src="movieDetail.cover" class="cover"></image>
			<view class="movieDesc">
				<view class="title">
					{{movieDetail.name}}
				</view>
				<view class="basic-info">
					{{movieDetail.originalName}}
				</view>
				<view class="basic-info">
					{{movieDetail.basicInfo}}
				</view>
				<view class="basic-info">
					{{movieDetail.totalTime}}
				</view>
				<view class="basic-info">
					{{movieDetail.releaseDate}}
				</view>
				<!-- 评分块start -->
				<view class="score-block">
					<view class="big-score">
						<view class="score-words">综合评分：</view>
						<view class="movie-score">{{movieDetail.score/10}}</view>
					</view>
					<view class="score-stars">
						<block v-if="movieDetail.score >= 0">
							<trailer-stars :innerScore="movieDetail.score" :showNum="false"></trailer-stars>
						</block>
						<view class="praise-counts">
							{{movieDetail.prisedCounts}} 人点赞
						</view>
					</view>
				</view>
				<!-- 评分块stop -->
			</view>
		</view>
		<!-- 影片信息end -->

		<!-- 分割线start -->
		<view class="line-wrapper">
			<view class="line"></view>
		</view>
		<!-- 分割线end -->

		<!-- 剧情介绍start -->
		<view class="plots-block">
			<view class="plots-title">
				剧情介绍：
			</view>
			<view class="plots-desc">
				{{movieDetail.plotDesc}}
			</view>
		</view>
		<!-- 剧情介绍end -->


	</view>
</template>

<script>
	import trailerStars from "../../components/trailerStars.vue";

	export default {
		data() {
			return {
				movieDetail: {}
			}
		},
		components: {
			trailerStars
		},
		methods: {

		},
		onLoad(params) {
			var trailerId = params.trailerId;

			// 通过API修改导航栏的属性
			uni.setNavigationBarColor({
				frontColor: "#ffffff",
				backgroundColor: "#000000"
			})

			var serverUrl = this.serverUrl;
			// 请求电影信息
			uni.request({
				url: serverUrl + '/movieInfo/movie?trailerId=' + trailerId,
				method: 'GET',
				data: {},
				success: res => {
					// 获取真实数据之前,务必判断状态为success
					if (res.data.status === "success") {
						this.movieDetail = res.data.data;
					}

				},
				fail: () => {},
				complete: () => {
					// uni.hideNavigationBarLoading();
					// uni.hideLoading();
					// uni.stopPullDownRefresh();
				}
			});
		},
		// 此函数仅仅只支持在小程序端的分享,分享到微信群或者微信好友
		onShareAppMessage() {
			return {
				title: this.movieDetail.name,
				path: '/pages/movie/movie?trailerId=' + this.movieDetail.id
			}
		},
		// 监听导航栏的按钮
		onNavigationBarButtonTap(e) {
			var index = e.index;
			var movieDetail = this.movieDetail;
			var trailerId = movieDetail.id;
			var trailerName = movieDetail.name;
			var cover = movieDetail.cover;
			var poster = movieDetail.poster;
			// index为0则分享
			if (index == 0) {
				uni.share({
					provider: 'weixin',
					type: 0,
					title: 'NEXT超英预告：《' + trailerName + '》',
					href: 'http://localhost/#/pages/movie/movie?trailerId=' + trailerId,
					summary: 'NEXT超英预告：《' + trailerName + '》',
					imageUrl: cover,
					success: () => {}
				});
			}
		}
	}
</script>

<style>
	@import url("movie.css");
</style>
