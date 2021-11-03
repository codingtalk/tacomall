
	function compare(time){
		let res;
		const timestamp = new Date(time.replace(/-/g, '/').valueOf());
		const currentTimes = new Date().getTime();
		res = currentTimes > timestamp ? true : false;
		return res;
	}

module.exports = {compare};