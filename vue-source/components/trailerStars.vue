<template name="trailerStars">
	<view class="movie-score-wrapper">
		<!-- // 注意：同级多个 v-for 时 key 的值是不允许重复的，key 的绑定是不支持表达式的，需要按照以下方式处理。
		// 错误的写法 -->
		<!-- 		<view v-for="(item, index) in list1" :key="index">{{item}}</view>
		<view v-for="(item, index) in list2" :key="index">{{item}}</view> -->
		<!-- // 正确的写法 -->
		<!-- 		<view>
			<view v-for="(item, index) in list1" :key="index">{{item}}</view>
		</view>
		<view>
			<view v-for="(item, index) in list2" :key="index">{{item}}</view>
		</view> -->
		<view>
			<image v-for="(yellow,index) in yellowScore" :key="index" src="/static/icos/star-yellow.png" class="star-ico"></image>
		</view>
		<view>
			<image v-for="(grey,index) in greyScore" :key="index" src="/static/icos/star-gray.png" class="star-ico"></image>
		</view>
		<view class="movie-score" v-if="showNum">
			{{innerScore / 10}}
		</view>
	</view>
</template>

<script>
	export default {
		data() {
			return {
				yellowScore: 0,
				greyScore: 5
			};
		},
		props: {
			innerScore: {
				type: Number,
				default: 0
			},
			showNum: {
				type: Boolean,
				default: false
			}
		},
		created() {
			var tmpScore = 0;
			if (this.innerScore != null && this.innerScore != undefined && this.innerScore != "") {
				tmpScore = this.innerScore;
			}
			var yellowScore = parseInt(tmpScore / 20);
			var greyScore = 5 - yellowScore;
			this.yellowScore = yellowScore;
			this.greyScore = greyScore;
		}
	}
</script>

<style>
	.movie-score-wrapper {
		display: flex;
		flex-direction: row;
	}

	.star-ico {
		width: 20upx;
		height: 20upx;
		/* margin-top: 6upx; */
	}

	.movie-score {
		font-size: 12px;
		color: grey;
		margin-left: 8upx;
	}
</style>
