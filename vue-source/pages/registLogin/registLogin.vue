<template>
	<view class="body">
		<form @submit="formSubmit">
			<view class="face-wrapper">
				<image src="../../static/icos/default-face.png" class="face"></image>
			</view>

			<view class="info-wrapper">
				<label class="words-lbl">账号</label>
				<input name="username" type="text" value="" class="input" placeholder="请输入用户名" placeholder-class="graywords" />
			</view>

			<view class="info-wrapper" style="margin-top: 40upx;">
				<label class="words-lbl">密码</label>
				<input name="password" type="text" value="" password="true" class="input" placeholder="请输入密码" placeholder-class="graywords" />
			</view>

			<button type="primary" form-type="submit" style="margin-top: 60upx;width:90%;">注册/登录</button>
		</form>

		<!-- 第三方登录H5不支持，所以隐藏掉 -->
		<!-- #ifndef H5 -->
		<view class="third-wrapper">
			<view class="third-line">
				<view class="single-line">
					<view class="line"></view>
				</view>

				<view class="third-words">第三方账号登录</view>

				<view class="single-line">
					<view class="line"></view>
				</view>
			</view>


			<view class="third-icos-wrapper">
				<!-- 5+app 用qq登录 小程序用微信小程序登录 h5不支持 -->
				<!-- #ifdef APP-PLUS -->
				<image src="../../static/icos/weixin.png" class="third-ico" data-logintype="weixin" @click="appOAuthLogin"></image>
				<image src="../../static/icos/QQ.png" class="third-ico" data-logintype="qq" @click="appOAuthLogin" style="margin-left: 80upx;"></image>
				<image src="../../static/icos/weibo.png" class="third-ico" data-logintype="sinaweibo" @click="appOAuthLogin" style="margin-left: 80upx;"></image>
				<!-- #endif -->
				<!-- #ifdef MP-WEIXIN -->
				<button open-type="getUserInfo" @getuserinfo="wxLogin" class="third-btn-ico"></button>
				<!-- #endif -->
			</view>
		</view>
		<!-- #endif -->
	</view>
</template>

<script>
	export default {
		data() {
			return {

			}
		},
		methods: {
			appOAuthLogin(e) {
				var me = this;
				// 获取用户的登录类型
				var logintype = e.currentTarget.dataset.logintype;
				// 授权登录
				uni.login({
					provider: logintype,
					success(loginRes) {
						// 授权登录成功以后,获取用户的信息
						uni.getUserInfo({
							provider: logintype,
							success(info) {
								console.log(JSON.stringify(info));
								var userInfo = info.userInfo;
								var face = "";
								var nickName = "";
								var openIdOrUid = "";
								if (logintype == "weixin") {
									face = userInfo.avatarUrl;
									nickName = userInfo.nickName;
									openIdOrUid = userInfo.openId;
								} else if (logintype == "qq") {
									face = userInfo.figureurl_qq_2;
									nickName = userInfo.nickname;
									openIdOrUid = userInfo.openId;
								} else if (logintype == "sinaweibo") {
									face = userInfo.avatar_large;
									nickName = userInfo.nickname;
									openIdOrUid = userInfo.id;
								}

								// 调用开发者后台,执行一键注册或登录
								var serverUrl = me.serverUrl;
								uni.request({
									url: serverUrl + '/user/appUnionLogin/' + logintype,
									data: {
										"face": face,
										"nickName": nickName,
										"openIdOrUid": openIdOrUid
									},
									method: "POST",
									success(result) {
										if (result.data.status == 200) {
											var userInfo = result.data.data;
											// 保存用户信息到全局的缓存中
											uni.setStorageSync("globalUser", userInfo);
											// 切换页面跳转，使用tab切换的api
											uni.switchTab({
												url: "../me/me",
											});
										}
									}
								})
							}
						})
					}
				});
			},
			// 实现在微信小程序端的微信登录
			wxLogin(e) {
				var me = this;
				// console.log(e);
				// 通过微信开发能力,获得微信用户的基本信息
				var userInfo = e.detail.userInfo;

				userInfo = {
					avatarUrl: 'http://122.152.205.72:88/group1/M00/00/05/CpoxxFw_-5-AFyVyAABLIH8xBTw233.png',
					nickName: 'Bob',
				}

				// 实现微信登录
				uni.login({
					provider: "weixin",
					success(loginResult) {
						// console.log(loginResult);
						// 获得微信登录的code: 授权码
						var code = loginResult.code;
						var serverUrl = me.serverUrl;
						uni.request({
							url: serverUrl + '/user/mpWXLogin/' + code,
							data: {
								"avatarUrl": userInfo.avatarUrl,
								"nickName": userInfo.nickName
							},
							method: "POST",
							success(userResult) {
								var userInfo = userResult.data.data;
								// 保存用户信息到全局的缓存中
								uni.setStorageSync("globalUser", userInfo);
								// 切换页面跳转，使用tab切换的api
								uni.switchTab({
									url: "../me/me"
								});
							}
						})
					}
				})
			},
			formSubmit(e) {
				var username = e.detail.value.username;
				var password = e.detail.value.password;

				// 发起注册/登录的请求
				var serverUrl = this.serverUrl;
				uni.request({
					url: serverUrl + '/user/registOrLogin',
					method: 'POST',
					data: {
						"username": username,
						"password": password
					},
					success: res => {
						// 如果查询到数据库中有该用户名，就是登录请求，否则是注册请求。这个部分的逻辑由后端完成
						// 200: 登录成功  201：注册成功   401：密码不对   404：用户名密码不能为空
						if (res.data.status == 200 || res.data.status == 201) {
							var userInfo = res.data.data;
							// 保存用户信息到全局的缓存中
							uni.setStorageSync("globalUser", userInfo);
							// 切换页面跳转，使用tab切换的api
							uni.switchTab({
								url: "../me/me"
							});
						} else if (res.data.status == 500 || res.data.status == 401) {
							uni.showToast({
								title: res.data.msg,
								duration: 2000,
								image: "../../static/icos/error.png"
							})
						}
					},
					fail: () => {},
					complete: () => {}
				});
			}

		}
	}
</script>

<style>
	@import url("registLogin.css");
</style>
