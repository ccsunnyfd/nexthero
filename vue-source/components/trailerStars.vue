<template name="trailerStars">
	<view class="movie-score-wrapper">
		<image v-for="(yellow,index) in yellowScore" :key=index src="../static/icos/star-yellow.png" class="star-ico"></image>
		<image v-for="(grey,index) in greyScore" :key=index src="../static/icos/star-gray.png" class="star-ico"></image>
		<view class="movie-score" v-if="showNum == 1">
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
			innerScore: 0,
			showNum: 0
		},
		created() {
			var tmpScore = 0;
			if(this.innerScore != null && this.innerScore != undefined && this.innerScore != "") {
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
		margin-top: 6upx;
	}

	.movie-score {
		font-size: 12px;
		color: grey;
		margin-left: 8upx;
	}
</style>
