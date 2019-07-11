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
						<trailer-stars :innerScore="movieDetail.score" :showNum="false"></trailer-stars>
						<view class="praise-counts">
							{{movieDetail.prisedCounts}} 人点赞
						</view>
					</view>
				</view>
				<!-- 评分块stop -->
			</view>
		</view>
		<!-- 影片信息end -->



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
		}
	}
</script>

<style>
	@import url("movie.css");
</style>
