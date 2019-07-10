<template>
	<view class="page">
		<!-- 搜索栏 start -->
		<view class="search-block">
			<view class="search-ico-wrapper">
				<image src="../../static/icos/search.png" class="search-ico"></image>
			</view>
			<input maxlength="10" placeholder="请输入搜索关键词" class="search-text" confirm-type="search" @confirm="searchMe"/>
		</view>
		<!-- 搜索栏 end -->

		<!-- 搜索结果海报列表 start -->
		<view class="movie-list-block page-block">
			<view v-for="movie in searchMoviesList" :key="movie.id" class="movie-poster-wrapper">
				<image :src="movie.cover" class="movie-poster"></image>
			</view>
		</view>
		<!-- 搜索结果海报列表 end -->

	</view>
</template>

<script>
	export default {
		data() {
			return {
				searchMoviesList: [],
				keywords: "",
				page: 1,
				pageSize: 15,
				totalPages: 1
			}
		},
		methods: {
			pagedTrailerList(keywords, page, pageSize) {
				uni.showLoading({
					mask: true,
					title: "请稍后..."
				});
				uni.showNavigationBarLoading();

				var serverUrl = this.serverUrl;
				// 请求搜索电影信息
				uni.request({
					url: serverUrl + '/movieInfo/search/list?' + 'keywords=' + keywords + '&page=' + page + '&size=' + pageSize,
					method: 'GET',
					data: {},
					success: res => {
						// 获取真实数据之前,务必判断状态为success
						if (res.data.status === "success") {
							var tmpList = res.data.data;
							this.searchMoviesList = this.searchMoviesList.concat(tmpList);
							this.totalPages = parseInt((res.data.totalCount - 1) / pageSize) + 1;  //获取总页数
							this.page = page;  // 覆盖当前页面里的page
						}

					},
					fail: () => {},
					complete: () => {
						uni.hideNavigationBarLoading();
						uni.hideLoading();
					}
				});
			},
			searchMe(e) {
				var val = e.detail.value;
				this.keywords = val;
				this.page = 1;
				this.searchMoviesList = [];
				this.pagedTrailerList(this.keywords, this.page, this.pageSize);
			}
		},
		onLoad() {
			this.pagedTrailerList('', 1, 15);
		},
		onReachBottom() {
			var page = this.page + 1;
			var keywords = this.keywords;
			var totalPages = this.totalPages;
			if(page > totalPages) {
				return;
			}
			this.pagedTrailerList(keywords, page, this.pageSize);
		}
	}
</script>

<style>
	@import url("search.css");
</style>
