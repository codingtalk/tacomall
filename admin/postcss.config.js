module.exports = {
	plugins: {
		'postcss-px-to-viewport-8-plugin': {
            unitToConvert: 'px',
            viewportWidth: 1920,
            viewportHeight: 1080,
            unitPrecision: 3,
            propList: [
              '*'
            ],
            viewportUnit: 'vw',
            fontViewportUnit: 'vw',
            selectorBlackList: [],
            minPixelValue: 1,
            mediaQuery: false,
            replace: true
		},
	},
};