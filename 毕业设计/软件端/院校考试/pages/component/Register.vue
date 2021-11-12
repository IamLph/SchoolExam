<template>
	<view class="content">
		<view class="login_from">

			<view class="login_from_input">
				<view class="login_from_name">学号</view>
				<view class="login_from_fun"><input type="number" v-model="userId" maxlength="10" placeholder="请输入学号"></view>
			</view>
			<view class="login_from_input">
				<view class="login_from_name">姓名</view>
				<view class="login_from_fun"><input type="text" v-model="name" maxlength="6" placeholder="请输入姓名"></view>
			</view>

			<view class="login_from_input">
				<view class="login_from_name">密码</view>
				<view class="login_from_fun"><input type="digit" maxlength="10" password="true" v-model="password1" placeholder="请输入密码"></view>
			</view>
			
			<view class="login_from_input">
				<view class="login_from_name">密码</view>
				<view class="login_from_fun"><input type="digit" maxlength="10" password="true" v-model="password2" placeholder="请再次输入密码"></view>
			</view>
			<view class="login_from_dl">
				<button @click="register">注册</button>
			</view>
		</view>

	</view>
</template>

<script>
	import * as api from '../../Api/api.js'
	export default{
		data(){
			return{
				userId:'',
				name:'',
				password1:'',
				password2:''
			}
		},
		methods:{
			register(){
				let userId = this.userId;
				let password1 = this.password1;
				let password2 = this.password2;
				let name = this.name;
				if(password1 === password2){
					if(userId.length == 10){
						if(name.length < 2){
							uni.showToast({
								icon:'none',
								title:"姓名格式不正确"
							})
						}else{
							if(password1.length < 6){
								uni.showToast({
									icon:'none',
									title:"密码长度应在6-10位"
								})
							}else{
								// 开始发送注册请求了
								api.registerUser({
									data:{
										name:name,
										userId:userId,
										password:password1
									},
									success:res=>{
										if(res.status == 200){
											uni.showModal({
												content:'注册成功，请前往登陆',
												success: function (res) {
													if (res.confirm) {
														uni.redirectTo({
															url:'Login'
														})
													}
												}
											})
										}else{
											uni.showToast({
												title:"该账号已被注册",
												icon:"none"
											})
										}
									},
									fail:err=>{
										uni.showToast({
											title:"网络延迟",
											icon:"none"
										})
									}
								})
							}
						}
					}else{
						uni.showToast({
							icon:'none',
							title:"请输入正确的10位学号"
						})
					}
				}else{
					uni.showModal({
						content:'密码不一致',
					})
				}
			}
		}
	}

</script>

<style>
	page {
		background: #fff;
	}
	.login_img {
		width: 100%;
		height: auto;
		margin-top: 90upx;
	}
	.login_img image {
		width: 170upx;
		height: 170upx;
		display: block;
		margin: 0px auto;
		border-radius: 50%;
	}

	.login_from {
		width: 100%;
		height: auto;
		box-sizing: border-box;
		padding: 20upx 8%;
	}

	.login_from_input {
		width: 100%;
		height: auto;
		display: flex;
		flex-direction: row;
		justify-content: space-between;
		align-items: center;
		border-bottom: 1px #eee solid;
		padding: 40upx 0px;
		margin: 0px auto;
	}

	.login_from_name {
		width: 20%;
		font-size: 36rpx;
	}

	.login_from_fun {
		width: 80%;
		display: flex;
		flex-direction: row;
		justify-content: flex-end;
		align-items: center;
	}

	.login_from_fun input {
		width: 100%;
		text-align: left;
		font-size: 14px;
	}


	.login_from_dl {
		width: 100%;
		height: 40px;
		line-height: 40px;
		margin-top: 150upx;
	}

	.login_from_dl button {
		width: 100%;
		height: 40px;
		line-height: 40px;
		background: #FF3B00;
		color: #fff;
		border-radius: 50px;
	}
	.logo_xieyi {
		width: 100%;
		height: 40px;
		line-height: 40px;
		display: flex;
		flex-direction: row;
		margin-top: 30upx;
		align-items: center;
		color: #444;
		font-size: 14px;
		justify-content: center;
	}

	.logo_xieyi label {
		font-size: 18px;
		margin-right: 1%;
	}

	.cuIcon-squarecheckfill {
		color: #FF3B00;
	}

	.logo_text text {
		color: #FF3B00;
	}

	.getyzm {
		width: 60%;
		display: flex;
		flex-direction: row;
		justify-content: flex-end;
		color: #FF3B00;
	}

	.cuicon {
		font-size: 18px;
	}
</style>
