<template>
	<view class="page page-fill">
		<form @submit="formSubmitBirthday">
			<view class="page-block" style="margin-top: 20upx;">
				<picker mode="date" name="birthday" @change="dateChange" >
					<view class="birthday">{{birthday}}</view>
				</picker>
			</view>
			<button type="primary" form-type="submit" class="submitBtn">提交</button>
		</form>
		<view class="graywords"></view>

	</view>
</template>

<script>
	export default {
		data() {
			return {
				birthday: "",
				globalUser: {}
			}
		},
		onLoad() {
			var me = this;
			var globalUser = me.getGlobalUser("globalUser");
			me.globalUser = globalUser;
			me.birthday = globalUser.birthday;
		},
		methods: {
			dateChange(e) {
				this.birthday = e.detail.value;
			},
			formSubmitBirthday(e) {
				var me = this;
				var birthday = e.detail.value.birthday;
				uni.request({
					url: me.serverUrl + '/user/modifyUserInfo',
					method: 'GET',
					data: {
						"id": me.globalUser.id,
						"birthday": birthday
					},
					header: {
						"headerUserId": me.globalUser.id,
						"headerUserToken": me.globalUser.userUniqueToken
					},
					method: "POST",
					success(res) {
						var resData = res.data;
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
					fail: () => {},
					complete: () => {}
				});
			}
		}
	}
</script>

<style>
	/* 页面铺满屏幕*/
	.page-fill {
		width: 100%;
		height: 100%;
		position: absolute;
	}

	.graywords {
		color: #EAEAEA;
	}

	.birth-input {
		background-color: white;
		height: 80upx;
		line-height: 40upx;
		margin-left: 40upx;
	}

	.birthday {
		background-color: white;
		height: 80upx;
	}

	.submitBtn {
		width: 95%;
		margin-top: 40upx;
	}
</style>
