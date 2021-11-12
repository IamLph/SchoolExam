<script>
	export default {
		onLaunch: function() {
			// console.log('App Launch')
			console.log("欢迎来到院校考试系统");
		},
		onShow: function() {
			// console.log('App Show')
		},
		onHide: function() {
			// console.log('App Hide')
		}
	}
</script>

<style>
	@import "./colorui/main.css";
	@import "./colorui/icon.css";
	/*每个页面公共css */
	page {
		background-color: #f7f7f7;
	}
	/* 解决ColorUI冲突问题 */
	uni-checkbox .uni-checkbox-input {
		border-radius: 3upx !important;
		color: #ffffff !important;
	}

	uni-checkbox .uni-checkbox-input.uni-checkbox-input-checked {
		color: #fff;
		border-color: rgb(0, 0, 0);
		background: rgb(0, 0, 0);
	}

	uni-checkbox .uni-checkbox-input.uni-checkbox-input-checked:after {
		font-size: 20px;
	}
</style>
