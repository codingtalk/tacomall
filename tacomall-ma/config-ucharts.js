const config = {
  'column': {
    'type': 'column',
    'canvasId': '',
    'canvas2d': false,
    'background': 'none',
    'animation': true,
    'timing': 'easeOut',
    'duration': 1000,
    'color': [
      '#000',
      '#f10',
      '#10f',
      '#12e'
      // '#1890FF',
      // '#91CB74',
      // '#FAC858',
      // '#EE6666',
      // '#73C0DE',
      // '#3CA272',
      // '#FC8452',
      // '#9A60B4',
      // '#ea7ccc'
    ],
    'padding': [
      15,
      15,
      0,
      5
    ],
    'rotate': false,
    'reserve': false,
    'fontSize': 13,
    'fontColor': '#666666',
    'enableScroll': false,
    'touchMoveLimit': 60,
    'enableMarkLine': false,
    'dataLabel': true,
    'dataPointShape': true,
    'dataPointShapeType': 'solid',
    'xAxis': {
      'disabled': false,
      'axisLine': true,
      'axisLineColor': '#CCCCCC',
      'calibration': false,
      'fontColor': '#666666',
      'fontSize': 13,
      'rotateLabel': false,
      'itemCount': 5,
      'boundaryGap': 'center',
      'disableGrid': true,
      'gridColor': '#CCCCCC',
      'gridType': 'solid',
      'dashLength': 4,
      'gridEval': 1,
      'scrollShow': false,
      'scrollAlign': 'left',
      'scrollColor': '#A6A6A6',
      'scrollBackgroundColor': '#EFEBEF'
    },
    'yAxis': {
      'disabled': false,
      'disableGrid': false,
      'splitNumber': 5,
      'gridType': 'solid',
      'dashLength': 8,
      'gridColor': '#CCCCCC',
      'padding': 10,
      'showTitle': false,
      'data': []
    },
    'legend': {
      'show': true,
      'position': 'bottom',
      'float': 'center',
      'padding': 5,
      'margin': 5,
      'backgroundColor': 'rgba(0,0,0,0)',
      'borderColor': 'rgba(0,0,0,0)',
      'borderWidth': 0,
      'fontSize': 13,
      'fontColor': '#666666',
      'lineHeight': 11,
      'hiddenColor': '#CECECE',
      'itemGap': 10
    },
    'extra': {
      'column': {
        'type': 'group',
        'width': 30,
        'seriesGap': 2,
        'barBorderCircle': false,
        'linearType': 'none',
        'linearOpacity': 1,
        'colorStop': 0,
        'meterBorde': 1,
        'meterFillColor': '#FFFFFF',
        'activeBgColor': '#000000',
        'activeBgOpacity': 0.08
      },
      'tooltip': {
        'showBox': true,
        'showArrow': true,
        'borderWidth': 0,
        'borderRadius': 0,
        'borderColor': '#000000',
        'borderOpacity': 0.7,
        'bgColor': '#000000',
        'bgOpacity': 0.7,
        'gridType': 'solid',
        'dashLength': 4,
        'gridColor': '#CCCCCC',
        'fontColor': '#FFFFFF',
        'splitLine': true,
        'horizentalLine': false,
        'xAxisLabel': false,
        'yAxisLabel': false,
        'labelBgColor': '#FFFFFF',
        'labelBgOpacity': 0.7,
        'labelFontColor': '#666666'
      },
      'markLine': {
        'type': 'solid',
        'dashLength': 4,
        'data': []
      }
    }
  }

}
export default config