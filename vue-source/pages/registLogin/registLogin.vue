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
	</view>
</template>

<script>
	export default {
		data() {
			return {

			}
		},
		methods: {
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
