<template>
	<view class="body">
		<view class="face-wrapper">
			<image src="../../static/icos/default-face.png" class="face"></image>
		</view>

		<view class="info-wrapper">
			<label class="words-lbl">账号</label>
			<input name="username" type="text" value="" class="input" placeholder="请输入用户名" placeholder-class="graywords"/>
		</view>

		<view class="info-wrapper" style="margin-top: 40upx;">
			<label class="words-lbl">密码</label>
			<input name="password" type="text" value="" password="true" class="input" placeholder="请输入密码" placeholder-class="graywords"/>
		</view>

		<button type="primary" style="margin-top: 60upx;width:90%;">注册/登录</button>
	</view>
</template>

<script>
	export default {
		data() {
			return {

			}
		},
		methods: {

		}
	}
</script>

<style>
	@import url("registLogin.css");
</style>
