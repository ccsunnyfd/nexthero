<template>
	<view class="black">
		<image :src="cover" mode="widthFix" @longpress="operator" class="cover"></image>
	</view>
</template>

<script>
	export default {
		data() {
			return {
				cover: '',
			}
		},
		onLoad(params) {
			this.cover = params.cover;
			// 通过API修改导航栏的属性
			uni.setNavigationBarColor({
				frontColor: "#ffffff",
				backgroundColor: "#000000"
			})
		},
		methods: {
			operator() {
				// #ifdef APP-PLUS || MP-WEIXIN
				uni.showActionSheet({
					itemList: ['保存图片到本地'],
					success: (res) => {
						// 下标为0则下载
						if (res.tapIndex == 0) {
							uni.showLoading({
								title: "图片保存中..."
							})
							uni.downloadFile({
								url: this.cover,
								success: (result) => {
									var tempFilePath = result.tempFilePath;
									uni.saveImageToPhotosAlbum({
										filePath: tempFilePath,
										success: () => {
											uni.showToast({
												title: "保存成功",
												duration: 2000
											})
										},
										complete: () => {
											uni.hideLoading();
										}
									})
								}
							})
						}
					}
				})
				// #endif
			}
		}
	}
</script>

<style>
	.black {
		background-color: black;
		width: 100%;
		height: 100%;
		position: fixed;
		display: flex;
		flex-direction: column;
		justify-content: center;
	}

	.cover {
		align-self: center;
	}
</style>
