<template>
	<view class="container">
		<view class="header" :style="{backgroundImage: 'url(' + bagUrl + ')'}">
			<view v-show="isLogin">
				<view class="photo" @click.stop="getInfo">
					<image :src="user.headImg"></image>
				</view>
				<view class="info">
					<view class="infoName">{{user.name}}</view>
					<view class="infoClooege">{{user.branch}}</view>
					<view class="infoClassName">{{user.grade}}</view>
				</view>
			</view>
			<view v-show="!isLogin">
				<view class="photo" @click.stop="getInfo">
					<image src="../../static/icon/kong.png"></image>
				</view>
			</view>
		</view>
		<!-- 功能 -->
		<view class="main">
			<view class="partContainer" @click="getInfo">
				<view class="itemContainer">
					<view class="leftIcon">
						<span class="cuIcon-people span1"></span>
					</view>
					<view class="content">
						<view class="title">个人信息</view>
					</view>
					<view class="rightIcon">
						<span class="cuIcon-right span2"></span>
					</view>
				</view>
			</view>
			
			<view class="partContainer"  @click="active">
				<view class="itemContainer">
					<view class="leftIcon">
						<span class="cuIcon-activity span1"></span>
					</view>
					<view class="content">
						<view class="title">我的考试</view>
					</view>
					<view class="rightIcon">
						<span class="cuIcon-right span2"></span>
					</view>
				</view>
			</view>
			
			<!-- <view class="partContainer" @click="setting">
				<view class="itemContainer">
					<view class="leftIcon">
						<span class="cuIcon-settings span1"></span>
					</view>
					<view class="content">
						<view class="title">设置</view>
					</view>
					<view class="rightIcon">
						<span class="cuIcon-right span2"></span>
					</view>
				</view>
			</view> -->
			
			<view class="partContainer" @click="Login" v-if="!isLogin">
				<view class="itemContainer">
					<view class="leftIcon">
						<span class="cuIcon-settings span1"></span>
					</view>
					<view class="content">
						<view class="title">登陆</view>
					</view>
					<view class="rightIcon">
						<span class="cuIcon-right span2"></span>
					</view>
				</view>
			</view>
			<view class="partContainer" @click="unLogin" v-else>
				<view class="itemContainer">
					<view class="leftIcon">
						<span class="cuIcon-settings span1"></span>
					</view>
					<view class="content">
						<view class="title">退出登陆</view>
					</view>
					<view class="rightIcon">
						<span class="cuIcon-right span2"></span>
					</view>
				</view>
			</view>
		</view>
	</view>
</template>

<script>
	import { bgImg ,user} from '../../data/AllData.js'
	import { isLogin } from '../../method/MethodApi.js'
	export default {
		data() {
			return {
				// 有一个默认用户头像
				user:user,
				bagUrl:bgImg,
				isLogin:false,
			}
		},
		onLoad() { 
			// 判断是否登陆
			this.isLogin = isLogin();
		},
		onShow(){
			if(this.isLogin){
				// 数据渲染获取缓存
				this.getSessionUser();
			}
		},
		methods:{
			// 获取登陆用户
			getSessionUser(){
				let user = uni.getStorageSync('user');
				this.user = user;
			},
			getInfo() {
				// console.log("查看个人详细信息");
				uni.navigateTo({
					url:"../Mine/Info"
				})
			},	
			// 活动
			active() {
				// console.log("我的考试")
				uni.navigateTo({
					url:'../Mine/MyExam'
				})
			},
			// 设置
			setting() {
				// goWithObj({url:'/pages/mine/Setting',method:'navigateTo'});
				console.log("设置");
				console.log(this.user)
			},
			Login(){
				// uni.navigateTo({
				// 	url:'../component/Login'
				// })
				uni.navigateTo({
					url:'../component/Start'
				})
			},
			unLogin(){
				console.log("退出登陆");
				uni.clearStorageSync('user');
				// uni.switchTab({
					
				// })
				location.reload();
			}
		}
	}	
</script>

<style>
	.container{
		width: 100%;
		height: 100%;
		background-color: rgba(248, 248, 249, 1.0);
	}
	.header{
		width: 100%;
		height: 460rpx;
		background-color: #760003;
		/* background-image: url(../../static/door.jpg); */
		background-position:center 80%;
		padding-top: 30rpx;
	}
	.photo{
		width: 25%;
		height: 25vw;
		background-color: #00aaff;
		margin: auto;
		border-radius: 50%;
	}
	.photo image{
		width: 100%;
		height: 100%;
		border-radius: 50%;
	}
	.info{
		margin-top: 30rpx;
		width: 100%;
		text-align: center;
		color: white;
	}
	.infoName{
		font-weight: 600;
		font-size: 40upx;
		margin-bottom: 15upx;
		font-size: 56upx;
		font-weight: 600;
	}
	.infoClooege{
		font-size: 44upx;
		font-weight: 500;
	}
	.infoClassName{
		font-size: 40upx;
		font-weight: 500;
	}
	.main{
		margin-top: 10upx;
		width: 100%;
		padding: 20upx 30upx 20upx 30upx;
	}
	.partContainer{
		width: 100%;
		height: 80upx;
		border-bottom: 1upx solid rgba(220,220,220,1);		
		padding-top: 10upx;
	}
	.itemContainer{
		width: 100%;
		height: 60upx;
		display: flex;
	}
	.leftIcon{
		width: 60upx;
		height: 60upx;
		line-height: 60upx;
	}
	.span1{
		font-size: 46upx;
	}
	.content{
		height: 60upx;
		line-height: 60upx;
	}
	.title{
		font-size: 36upx;
		font-weight: 500;
	}
	.rightIcon{
		position: absolute;
		right: 28upx;
		height: 60upx;
		line-height: 60upx;
		color: gray;
	}
	.span2{
		font-size: 36upx;
		color: #acacac;
	}
	.partContainer:last-child{
		border: 0;
	}
	.span3{
		float: right;
		color: #acacac;
		margin-right: 15upx;
		font-size: 35upx;
	}
	.btnContainer{
		width: 100%;
		height: 80upx;
		position: absolute;
		bottom: 40upx;
	}
	.loginOutBtn{
		width: 70%;
		height: 70upx;
		line-height: 70upx;
		font-size: 40upx;
		background-color: red;
		color: white;
		border-radius: 0.4em;
	}
</style>
