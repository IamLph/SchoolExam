<template>
	<!-- 请选择考试类型 -->
	<view class="Container">
		<view class="title">请选择考试类型</view>
		<view class="typeContainer">
			<view class="type" @tap="practice(1)">
				<view class="imgContainer">
					<image src="../../static/image/img/type.png" class="img"></image>
				</view>
				<view class="content">单项选择</view>
			</view>
			<view class="type" @tap="practice(2)">
				<view class="imgContainer" >
					<image src="../../static/image/img/type.png" class="img"></image>
				</view>
				<view class="content">多项选择</view>
			</view>
			<view class="type" @tap="practice(3)">
				<view class="imgContainer">
					<image src="../../static/image/img/type.png" class="img"></image>
				</view>
				<view class="content">判断题</view>
			</view>
			<view class="type" @tap="practice(4)">
				<view class="imgContainer">
					<image src="../../static/image/img/type.png" class="img"></image>
				</view>
				<view class="content">综合练习</view>
			</view>
		</view>
	</view>
</template>

<script>
	export default{
		data(){
			return{
				course_id:'',
			}
		},
		onLoad(e) {
			// console.log(e.id);
			this.course_id = e.id;
		},
		methods:{
			practice(e){
				// uni.redirectTo({
				// 	url:'practice?course_id='+this.course_id+'&type='+e
				// })
				uni.navigateTo({
					url:'practice?course_id='+this.course_id+'&type='+e
				})
			}
		}
	}
</script>

<style>
	page{
		height: 100%;
		width: 100%;
	}
	.Container{
		height: 100%;
		width: 100%;
		padding-top: 20rpx;
		padding: 20rpx 20rpx 40rpx 20rpx;
	}
	.title{
		font-size: 35rpx;
		font-weight: 600;
		margin-left: 10rpx;
	}
	.typeContainer{
		margin-top: 20rpx;
		width: 100%;
		height: 93%;
	}
	.type{
		margin-top: 10rpx;
		height: 150rpx;
		width: 100%;
		background-color: #fffbfc;
		display: flex;
		padding: 10rpx;
		box-shadow:-1px 1px 2px 1px rgba(240, 240, 240, 1.0);
	}
	.imgContainer{
		width: 130rpx;
		height: 130rpx;
		line-height: 130rpx;
	}
	.img{
		width: 120rpx;
		height: 120rpx;
	}
	.content{
		line-height: 120rpx;
		font-size: 40rpx;
		font-weight: 600;
		padding-left: 20rpx;
	}
</style>
