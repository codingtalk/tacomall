<template>
	<div class="_500">
		<div class="_5-content">
			<img src="/image/500.png" />
			<h1>500 Something Exceptional Had Happened In Server</h1>
			<div class="btn">
				<span>返回首页</span>
			</div>
			<div class="guide">
				<p></p>
			</div>
		</div>
	</div>
</template>

<style lang="less">
._500 {
	display: flex;
	justify-content: center;

	._5-content {
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