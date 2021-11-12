<template>
	<view class="newsScoView">
		<block v-for="(item,index) in examNotice" :key="index">
			<view v-if="all">
				<view class="oneCard" @tap="getInfo(item)">
					<view class="FirstLine">
						<view class="First_Left">
							[{{item.id}}]{{item.type}}
						</view>
						<view class="First_Right">
							<view class="First_Right_Time">
								剩余{{item.surTime}}天
							</view>
						</view>
					</view>
					<view class="SecondLine">
						<view class="Second_Left">
							监考老师:
							<text class="second_text">{{item.teacher}}</text>
						</view>
					</view>
					<view class="SecondLine">
						<view class="Second_Left">
							日期:
							<text class="second_text">{{item.time}}</text>
						</view>
					</view>
					<view class="SecondLine">
						<view class="Second_Left">
							时间:
							<text class="second_text">{{item.detailTime}}</text>
						</view>
					</view>
				</view>
			</view>
			<view v-else>
				<view class="oneCard" v-if="index < num" @tap="getInfo(item)">
					<view class="FirstLine">
						<view class="First_Left">
							[{{item.id}}]{{item.type}}
						</view>
						<view class="First_Right">
							<view class="First_Right_Time">
								剩余{{item.surTime}}天
							</view>
						</view>
					</view>
					<view class="SecondLine">
						<view class="Second_Left">
							监考老师:
							<text class="second_text">{{item.teacher}}</text>
						</view>
					</view>
					<view class="SecondLine">
						<view class="Second_Left">
							日期:
							<text class="second_text">{{item.time}}</text>
						</view>
					</view>
					<view class="SecondLine">
						<view class="Second_Left">
							时间:
							<text class="second_text">{{item.detailTime}}</text>
						</view>
					</view>
				</view>
			</view>
		</block>
	</view>
</template>

<script>
	import {
		DEFAULTNEWSCOUNT
	} from '../../data/DataSetting.js'
	export default {
		props:["examNotice","all"],
		methods: {
			getInfo(item){
				let obj = JSON.stringify(item)
				uni.navigateTo({
					url: '../Exam/IntoExam?item=' + encodeURIComponent(obj)
				})
			}
		},
		data(){
			return{
				num:DEFAULTNEWSCOUNT
			}
		}
	}
</script>

<style>
	.newsScoView {
		width: 94%;
		margin-left: 3%;
		margin-top: 40upx;
	}
	
	.oneCard {
		width: 100%;
		background-color: #007AFF;
		height: 220rpx;
		background: white;
		box-shadow: -1px 1px 2px 1px rgba(205, 205, 205, 1);
		padding: 5px 10px 5px 12px;
		margin-top: 20rpx;
		position: relative;
		margin-top: 40upx;
	}
	
	.FirstLine {
		display: flex;
		width: 100%;
		height: 50rpx;
	}
	
	.First_Left {
		line-height: 50rpx;
		width: 60%;
		font-size: 30rpx;
		color: #000000;
		font: bold;
	}
	
	.First_Right {
		line-height: 50rpx;
		width: 30%;
		margin-left: 10%;
		text-align: right;
	}
	
	.First_Right_Time {
		width: 150rpx;
		height: 50rpx;
		border-radius: 0.2em;
		float: right;
		text-align: center;
		color: #FFFFFF;
		font-weight: 400;
		background-color: #ef1233;
	}
	
	.SecondLine:first {
		margin-top: 15rpx;
	}
	
	.SecondLine {
		width: 100%;
		height: 50rpx;
		line-height: 50rpx;
	}
	
	.Second_Left {
		font-size: 28rpx;
		color: #636350;
	}
	
	.second_text {
		margin-left: 30rpx;
	}
</style>
