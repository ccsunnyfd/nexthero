<template>
	<view class="black">
		<image :src="cover" mode="widthFix" class="cover"></image>
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
