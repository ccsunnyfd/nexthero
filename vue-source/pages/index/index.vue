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
				<view class="single-poster" v-for="movie in hotSuperHeroList" :key=movie.id>
					<view class="poster-wrapper">
						<image :src="movie.cover" class="poster"></image>
						<view class="movie-name">{{movie.name}}</view>
						<trailer-stars :innerScore="movie.score" showNum="1"></trailer-stars>
					</view>
				</view>
			</scroll-view>
		</view>
		<!-- 热门超英 end -->

	</view>
</template>

<script>
	// import common from "../../common/common.js";
	import trailerStars from "../../components/trailerStars.vue";
	
	export default {
		data() {
			return {
				carouselList: [],
				hotSuperHeroList: []
			}
		},
		components: {
			trailerStars
		},
		onLoad() {
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

			// 请求热门超英电影海报信息
			uni.request({
				url: serverUrl + '/movieInfo/list',
				method: 'POST',
				data: {},
				success: res => {
					// 获取真实数据之前,务必判断状态为success
					if (res.data.status === "success") {
						this.hotSuperHeroList = res.data.data;
					}

				},
				fail: () => {},
				complete: () => {}
			});

		},
		methods: {

		}
	}
</script>

<style>
	@import url("index.css");
</style>
