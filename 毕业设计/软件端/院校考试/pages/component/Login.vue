<template>
	<view class="content">
		<view class="avatorWrapper">
			<view class="avator" style="border-radius: 50%;">
				<image class="img" src="../../static/icon/kong.png" mode="widthFix" style="border-radius: 50%;"></image>
			</view>
		</view>
		<view class="form">
			<view class="inputWrapper">
				<input class="input" type="number" maxlength="10" v-model="userId" placeholder="请输入用户名" />
			</view>
			<view class="inputWrapper">
				<input class="input" type="password" maxlength="100" v-model="password" placeholder="请输入密码" />
			</view>
			<view class="loginBtn" @tap="login">
				<text class="btnValue">登录</text>
			</view>
			<view class="bottom">
				<text @tap="register" class="textA">注册账号</text>
				<text @tap="forget" class="textB">找回密码</text>
			</view>
			
		</view>
	</view>
</template>

<script>
	// import {
	// 	user
	// } from '../../data/AllData.js'
	import * as api from '../../Api/api.js'
	export default {
		data() {
			return {
				title: 'Hello',
				userId: '',
				password: '',
			}
		},
		onLoad() {
		},
		methods: {
			login() {
				let that = this
				let userId = this.userId
				let password = this.password

				if (userId == '' || password == '') {
					uni.showToast({
						title: "账号密码不可为空",
						icon: "none"
					})
				} else {
					let user = this.user
					api.loginUser({
						data:{
							userId:userId,
							password:password
						},success:res=>{
							if(res.status == 200){
								let user = res.data;
								uni.setStorage({
									key:'user',
									data:user,
									success(){
										uni.reLaunch({
											url:'../tabbar/Index'
										})
									}
								})
							}else{
								uni.showToast({
									title:"账号或密码错误",
									icon:'none'
								})
							}
						},fail:err=>{
							uni.showToast({
								title:"网络延迟",
								icon:"none"
							})
						}
					})
					// 模拟登陆
					// if (userId === user.userId && password === user.password) {
					// 	// console.log("登陆成功")
					// 	uni.setStorage({
					// 		key:'user',
					// 		data:user,
					// 		success(){
					// 			// console.log("登陆成功设置缓存数据并且跳转到首页")
					// 			uni.reLaunch({
					// 				url:'../tabbar/Index'
					// 			})
					// 		}
					// 	})
					// } else {
					// 	uni.showToast({
					// 		title:'账号或者密码错误',
					// 		icon:'none'
					// 	})
					// }
				}
			},
			register(){
				uni.navigateTo({
					url:'Register'
				})
			},
			forget() {
				uni.showToast({
					title: '请联系老师或管理员重置密码',
					icon: 'none'
				});
			},
		},
	}
</script>

<style lang="css">
	page {
		height: 100%;
		width: 100%;
	}

	.content {
		background: #329da2;
		width: 100%;
		height: 100%;
	}

	.avatorWrapper {
		height: 30vh;
		width: 100vw;
		display: flex;
		justify-content: center;
		align-items: flex-end;
	}

	.avator {
		width: 200upx;
		height: 200upx;
		overflow: hidden;
	}

	.avator .img {
		width: 100%
	}

	.form {
		padding: 0 100upx;
		margin-top: 80px;
	}

	.inputWrapper {
		width: 100%;
		height: 80upx;
		background: white;
		border-radius: 20px;
		box-sizing: border-box;
		padding: 0 20px;
		margin-top: 25px;
	}

	.inputWrapper .input {
		width: 100%;
		height: 100%;
		text-align: center;
		font-size: 15px;
	}

	.loginBtn {
		width: 100%;
		height: 80upx;
		background: #77B307;
		border-radius: 50upx;
		margin-top: 50px;
		display: flex;
		justify-content: center;
		align-items: center;

	}

	.loginBtn .btnValue {
		color: white;
	}
	.bottom{
		width: 100%;
		height: 20px;
		line-height: 20px;
		display: flex;
		padding-left: 120rpx;
		margin-top: 20rpx;
		text-align: center;
		color: #EAF6F9;
		font-size: 15px;
	}
	.textB{
		margin-left: 20px;
	}
</style>
