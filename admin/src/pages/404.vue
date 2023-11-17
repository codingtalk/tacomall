<template>
	<div class="_404">
		<div class="_4-content">
			<img src="/image/404.png" />
			<h1>404 Page Not Found</h1>
			<div class="btn">
				<span>返回登录页</span>
			</div>
		</div>
	</div>
</template>

<script setup>
</script>

<style lang="less">
._404 {
	display: flex;
	justify-content: center;

	._4-content {
		display: flex;
		flex-direction: column;
		align-items: center;
		width: 300px;
		padding: 100px 0;

		img {
			width: 100%;
		}

		h1 {
			padding-top: 20px;
			white-space: nowrap;
		}

		.btn {
			display: flex;
			align-items: center;
			justify-content: center;
			width: 100px;
			height: 30px;
			background: #1890ff;
			margin-top: 50px;

			span {
				color: white;
			}

			&:hover {
				cursor: pointer;
			}
		}
	}
}
</style>