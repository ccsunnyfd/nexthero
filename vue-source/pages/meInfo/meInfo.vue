<template>
	<view class="page page-fill">
		<view class="page-block info-list">
			<!-- 头像 -->
			<view class="item-wrapper face-line-upbottom">
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
			<view class="item-wrapper">
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
			<view class="item-wrapper">
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
			<view class="item-wrapper">
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
