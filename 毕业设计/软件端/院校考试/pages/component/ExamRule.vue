<template>
	<!-- 考试规定 -->
	<view class="Container">
		<view class="title">《平顶山学院考试规定》</view>
		<view class="sv_container">
			<scroll-view scroll-y class="SV">
				<block v-for="(item,index) in EXAMRULE" :key="index">
					<view class="Content">{{item.content}}</view>
				</block>
			</scroll-view>
		</view>
	</view>
</template>

<script>
	import {EXAMRULE} from '../../data/DataSetting.js'
	export default{
		data(){
			return{
				EXAMRULE:EXAMRULE
			}
		},
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
		padding: 10rpx 20rpx 30rpx 20rpx;
	}
	.title{
		font-size: 45rpx;
		font-size: 600;
		text-align: center;
	}
	.sv_container{
		width: 100%;
		height: 94%;
	}
	.SV{
		width: 100%;
		height: 100%;
		margin-top: 20rpx;
	}
	.Content{
		font-size: 38rpx;
		margin-top: 10rpx;
	}
</style>
