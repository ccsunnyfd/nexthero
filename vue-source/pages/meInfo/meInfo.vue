<template>
	<view class="page page-fill">
		<view class="page-block info-list">
			<!-- 头像 -->
			<view class="item-wrapper face-line-upbottom" @click="operator">
				<view class="info-words">头像</view>

				<view class="right-wrapper">
					<image :src="globalUser.faceImage" class="face"></image>
					<view class="arrow-block">
						<image src="../../static/icos/left-gray-arrow.png" class="arrow-ico"></image>
					</view>
				</view>
			</view>
			<view class="line-top">
				<view class="line"></view>
			</view>

			<!-- 昵称 -->
			<view class="item-wrapper" @click="modifyNickname">
				<view class="info-words">昵称</view>

				<view class="right-wrapper">
					<view class="gray-fields">{{globalUser.nickname}}</view>
					<view class="arrow-block">
						<image src="../../static/icos/left-gray-arrow.png" class="arrow-ico"></image>
					</view>
				</view>
			</view>
			<view class="line-top">
				<view class="line"></view>
			</view>

			<!-- 生日 -->
			<view class="item-wrapper" @click="modifyBirthday">
				<view class="info-words">生日</view>

				<view class="right-wrapper">
					<view class="gray-fields">{{globalUser.birthday}}</view>
					<view class="arrow-block">
						<image src="../../static/icos/left-gray-arrow.png" class="arrow-ico"></image>
					</view>
				</view>
			</view>
			<view class="line-top">
				<view class="line"></view>
			</view>

			<!-- 性别 -->
			<view class="item-wrapper" @click="modifySex">
				<view class="info-words">性别</view>

				<view class="right-wrapper">
					<view class="gray-fields">
						<view v-if="globalUser.sex == 'm'">
							男
						</view>
						<view v-else-if="globalUser.sex == 'f'">
							女
						</view>
						<view v-else>
							未选择
						</view>
					</view>
					<view class="arrow-block">
						<image src="../../static/icos/left-gray-arrow.png" class="arrow-ico"></image>
					</view>
				</view>
			</view>

		</view>

		<view class="footer-wrapper">
			<view class="footer-words" @click="cleanStorage">
				清理缓存
			</view>
			<view class="footer-words" style="margin-top: 10upx;" @click="logout">
				退出登录
			</view>
		</view>

	</view>
</template>

<script>
	export default {
		data() {
			return {
				globalUser: {}
			}
		},
		onShow() {
			this.globalUser = this.getGlobalUser("globalUser");
		},
		methods: {
			modifyNickname() {
				uni.navigateTo({
					url: "../meNickname/meNickname"
				})
			},
			modifyBirthday() {
				uni.navigateTo({
					url: "../meBirthday/meBirthday"
				})
			},
			modifySex() {
				uni.navigateTo({
					url: "../meSex/meSex"
				})
			},
			operator() {
				var me = this;
				var globalUser = me.getGlobalUser("globalUser");
				uni.showActionSheet({
					itemList: ["查看我的头像", "从相册选择上传"],
					success(res) {
						var index = res.tapIndex;
						if (index == 0) {
							// 预览头像
							var faceArr = [];
							faceArr.push(globalUser.faceImage);
							uni.previewImage({
								urls: faceArr,
								current: faceArr[0]
							})
						} else if (index == 1) {
							// 选择上传头像
							uni.chooseImage({
								count: 1,
								sizeType: ["compressed"],
								sourceType: ["album", "camera"],
								success(res) {
									// 获得临时路径
									var tempFilePath = res.tempFilePaths[0];
									uni.navigateTo({
										url: "../meFace/meFace?tempFilePath=" + tempFilePath
									})
								}
							})
						}
					}
				});
			},
			cleanStorage() {
				uni.clearStorageSync();
				uni.showToast({
					title: "清理缓存成功",
					mask: false,
					duration: 1500
				})
			},
			logout() {
				var me = this;
				var globalUser = me.getGlobalUser("globalUser");
				if (globalUser != null) {
					var currentUserId = globalUser.id;
					// 发起退出登录的请求
					var serverUrl = me.serverUrl;
					uni.request({
						url: serverUrl + '/user/logout?userId=' + currentUserId,
						method: 'POST',
						data: {},
						success: res => {
							if (res.data.status == 200) {
								uni.removeStorageSync("globalUser");
								uni.switchTab({
									url: "../me/me"
								});
							}
						},
						fail: () => {},
						complete: () => {}
					});
				} else {
					uni.switchTab({
						url: "../me/me"
					});
				}
			}
		}
	}
</script>

<style>
	@import url("meInfo.css");
</style>
