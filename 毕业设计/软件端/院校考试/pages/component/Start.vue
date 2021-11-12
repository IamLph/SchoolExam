<template>
	<view class="imgContainer">
		<image src="../../static/img/background1.png" class="img" mode="scaleToFill"></image>
	</view>
	
</template>

<script>
	export default {
		data() {
			return {

			}
		},
		onLoad() {
			this.start()
		},
		methods: {
			start(e) {
				setTimeout(function() {
					uni.reLaunch({
						url:'Login'
					})
				}, 3000);
			}
		}
	}
</script>
<style>
	page{
		width: 100%;
		height: 100%;
		padding: 0;
		margin: 0;
		background-image: url(../../static/img/background1.png);
	}
	.imgContainer{
		width: 100%;
		height: 100%;
		padding: 0;
		margin: 0;
	}
	.img{
		width: 100%;
		height: 100%;
		display: block;
	}
</style>
