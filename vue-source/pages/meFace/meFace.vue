<template>
	<view class="page page-fill">
		<view class="pending-wrapper">
			<image id="face" :src="tempFace" class="pending-face" mode="scaleToFill"></image>
		</view>

		<view class="notice">
			<view class="notice-words">
				* 请从相册中选择等比宽高的图片噢~
			</view>
		</view>

		<view class="footer-area">
			<view class="line"></view>
			<view class="footer-operator">
				<view class="operator-words" @click="changePendingFace">
					重新选择
				</view>
				<view class="operator-words" @click="upload">
					确认上传
				</view>
			</view>
		</view>

	</view>
</template>

<script>
	export default {
		data() {
			return {
				tempFace: ""
			}
		},
		onLoad(params) {
			var tempFilePath = params.tempFilePath;
			this.tempFace = tempFilePath;
		},
		methods: {
			// 重新选择头像
			changePendingFace() {
				var me = this;
				uni.chooseImage({
					count: 1,
					sizeType: ["compressed"],
					sourceType: ["album", "camera"],
					success(res) {
						// 获得临时路径
						var tempFilePath = res.tempFilePaths[0];
						me.tempFace = tempFilePath;
					}
				})
			},
			upload() {
				var me = this;
				var globalUser = me.getGlobalUser("globalUser");

				uni.showLoading({
					mask: true,
					title: "上传中，请稍后"
				});

				uni.uploadFile({
					url: me.serverUrl + "/user/uploadFace?userId=" + globalUser.id,
					filePath: me.tempFace,
					name: "file",
					header: {
						"headerUserId": globalUser.id,
						"headerUserToken": globalUser.userUniqueToken
					},
					success(res) {
						var resDataStr = res.data;
						var resData = JSON.parse(resDataStr);
						if (resData.status == 200) {
							// 获得最新的用户数据
							var userInfo = resData.data;
							uni.setStorageSync("globalUser", userInfo);
							uni.navigateBack({
								delta: 1
							})
							// } else if (resData.status == 502 || resData.status == 500) {
						} else {
							uni.showToast({
								title: resData.msg,
								image: "../../static/icos/error.png",
								duration: 2000
							});
						}
					},
					complete() {
						uni.hideLoading();
					}
				});
			}
		}
	}
</script>

<style>
	.page-fill {
		display: flex;
		flex-direction: column;
		justify-content: flex-start;
		width: 100%;
		height: 100%;
		position: absolute;
		background-color: #000000;
	}

	.pending-wrapper {
		margin: 40upx 40upx;
		align-self: center;
		display: flex;
		flex-direction: row;
		justify-content: center;
	}

	.pending-face {
		width: 600upx;
		height: 600upx;
	}

	.notice {
		margin-top: 20upx;
		align-self: center;
		display: flex;
		flex-direction: row;
		justify-content: center;
	}

	.notice-words {
		font: 10px;
		color: dimgrey;
	}

	.footer-area {
		position: fixed;
		bottom: 40upx;
		width: 100%;
	}
	.line {
		height: 5upx;
		width: 100%;
		color: #4F4F4F;
	}

	.footer-operator {
		padding: 20upx 40upx 20upx 40upx;
		display: flex;
		flex-direction: row;
		justify-content: space-between;
	}

	.operator-words {
		font: 14px;
		background-color: #4F4F4F;
		border-radius: 5upx;
		color: #F7F4F9;
		padding: 10upx;
	}
</style>
