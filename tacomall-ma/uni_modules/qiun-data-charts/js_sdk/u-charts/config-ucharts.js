/*
 * uCharts®
 * 高性能跨平台图表库，支持H5、APP、小程序（微信/支付宝/百度/头条/QQ/360）、Vue、Taro等支持canvas的框架平台
 * Copyright (c) 2021 QIUN®秋云 https://www.ucharts.cn All rights reserved.
 * Licensed ( http://www.apache.org/licenses/LICENSE-2.0 )
 * 复制使用请保留本段注释，感谢支持开源！
 * 
 * uCharts®官方网站
 * https://www.uCharts.cn
 * 
 * 开源地址:
 * https://gitee.com/uCharts/uCharts
 * 
 * uni-app插件市场地址：
 * http://ext.dcloud.net.cn/plugin?id=271
 * 
 */

// 主题颜色配置：如每个图表类型需要不同主题，请在对应图表类型上更改color属性
const color = [ 
  // axq
  '#9054EF','#4ADEE0','#5371F0','#F17891',
  //
  '#1890FF', '#91CB74', '#FAC858', '#EE6666', '#73C0DE', '#3CA272', '#FC8452', '#9A60B4', '#ea7ccc' ];

module.exports = {
  //demotype为自定义图表类型
  'type': [ 'pie','ring','rose','word','funnel','map','arcbar','line','column','area','radar','gauge','candle','mix','demotype' ],
  'range': [ '饼状图','圆环图','玫瑰图','词云图','漏斗图','地图','圆弧进度条','折线图','柱状图','区域图','雷达图','仪表盘','K线图','混合图','自定义类型' ],
  //增加自定义图表类型，如果需要categories，请在这里加入您的图表类型例如最后的"demotype"
  'categories': [ 'line','column','area','radar','gauge','candle','mix','demotype' ],
  //instance为实例变量承载属性，option为eopts承载属性，不要删除
  'instance': {},
  'option': {},
  //下面是自定义format配置，因除H5端外的其他端无法通过props传递函数，只能通过此属性对应下标的方式来替换
  'formatter': {
    'yAxisDemo1': function (val){return val+'元'},
    'yAxisDemo2': function (val){return val.toFixed(2)},
    'seriesDemo1': function (val){
      return val+'元'
    },
    'tooltipDemo1': function (item, category, index, opts){
      if (index==0){
        return '随便用'+item.data+'年'
      } else {
        return '其他我没改'+item.data+'天'
      }
    },
    'pieDemo': function (val, index, series){
      if (index !== undefined){
        return series[index].name+'：'+series[index].data+'元'
      }
    },
    'cost': function (val, index, series){
      if (index !== undefined){
        return series[index].name+'\n'+series[index].data+'元'
      }
    }
  },
  //这里演示了自定义您的图表类型的option，可以随意命名，之后在组件上 type="demotype" 后，组件会调用这个花括号里的option，如果组件上还存在opts参数，会将demotype与opts中option合并后渲染图表。
  'demotype': {
    //我这里把曲线图当做了自定义图表类型，您可以根据需要随意指定类型或配置
    'type': 'line',
    'color': color,
    'padding': [ 15,10,0,15 ],
    'xAxis': {
      'disableGrid': true
    },
    'yAxis': {
      'gridType': 'dash',
      'dashLength': 2
    },
    'legend': {
    },
    'extra': {
      'line': {
        'type': 'curve',
        'width': 2
      }
    }
  },
  //下面是自定义配置，请添加项目所需的通用配置
  'pie': {
    'type': 'pie',
    'color': color,
    'padding': [ 5,5,5,5 ],
    'extra': {
      'pie': {
        'activeOpacity': 0.5,
        'activeRadius': 10,
        'offsetAngle': 0,
        'labelWidth': 15,
        'border': true,
        'borderWidth': 3,
        'borderColor': '#FFFFFF'
      }
    }
  },
  'ring': {
    'type': 'ring',
    'color': color,
    'padding': [ 5,5,5,5 ],
    'rotate': false,
    'dataLabel': true,
    'legend': {
      'show': true,
      'position': 'right',
      'lineHeight': 25
    },
    'title': {
      'name': '收益率',
      'fontSize': 15,
      'color': '#666666'
    },
    'subtitle': {
      'name': '70%',
      'fontSize': 25,
      'color': '#7cb5ec'
    },
    'extra': {
      'ring': {
        'ringWidth': 30,
        'activeOpacity': 0.5,
        'activeRadius': 10,
        'offsetAngle': 0,
        'labelWidth': 15,
        'border': true,
        'borderWidth': 3,
        'borderColor': '#FFFFFF'
      }
    }
  },
  'rose': {
    'type': 'rose',
    'color': color,
    'padding': [ 5,5,5,5 ],
    'legend': {
      'show': true,
      'position': 'left',
      'lineHeight': 25
    },
    'extra': {
      'rose': {
        'type': 'area',
        'minRadius': 50,
        'activeOpacity': 0.5,
        'activeRadius': 10,
        'offsetAngle': 0,
        'labelWidth': 15,
        'border': false,
        'borderWidth': 2,
        'borderColor': '#FFFFFF'
      }
    }
  },
  'word': {
    'type': 'word',
    'color': color,
    'extra': {
      'word': {
        'type': 'normal',
        'autoColors': false
      }
    }
  },
  'funnel': {
    'type': 'funnel',
    'color': color,
    'padding': [ 15,15,0,15 ],
    'extra': {
      'funnel': {
        'activeOpacity': 0.3,
        'activeWidth': 10,
        'border': true,
        'borderWidth': 2,
        'borderColor': '#FFFFFF',
        'fillOpacity': 1,
        'labelAlign': 'right'
      }
    }
  },
  'map': {
    'type': 'map',
    'color': color,
    'padding': [ 0,0,0,0 ],
    'dataLabel': true,
    'extra': {
      'map': {
        'border': true,
        'borderWidth': 1,
        'borderColor': '#666666',
        'fillOpacity': 0.6,
        'activeBorderColor': '#F04864',
        'activeFillColor': '#FACC14',
        'activeFillOpacity': 1
      }
    }
  },
  'arcbar': {
    'type': 'arcbar',
    'color': color,
    'title': {
      'name': '百分比',
      'fontSize': 25,
      'color': '#00FF00'
    },
    'subtitle': {
      'name': '默认标题',
      'fontSize': 15,
      'color': '#666666'
    },
    'extra': {
      'arcbar': {
        'type': 'default',
        'width': 12,
        'backgroundColor': '#E9E9E9',
        'startAngle': 0.75,
        'endAngle': 0.25,
        'gap': 2
      }
    }
  },
  'line': {
    'type': 'line',
    'color': color,
    'padding': [ 15,10,0,15 ],
    'xAxis': {
      'disableGrid': true
    },
    'yAxis': {
      'gridType': 'dash',
      'dashLength': 2
    },
    'legend': {
    },
    'extra': {
      'line': {
        'type': 'straight',
        'width': 2
      }
    }
  },
  'column': {
    'type': 'column',
    'color': color,
    'padding': [ 15,15,0,5 ],
    'xAxis': {
      'disableGrid': true
    },
    'yAxis': {
    },
    'legend': {
    },
    'extra': {
      'column': {
        'type': 'group',
        'width': 30,
        'meterBorde': 1,
        'meterFillColor': '#FFFFFF',
        'activeBgColor': '#000000',
        'activeBgOpacity': 0.08
      }
    }
  },
  'area': {
    'type': 'area',
    'color': color,
    'padding': [ 15,15,0,15 ],
    'xAxis': {
      'disableGrid': true
    },
    'yAxis': {
      'gridType': 'dash',
      'dashLength': 2
    },
    'legend': {
    },
    'extra': {
      'area': {
        'type': 'straight',
        'opacity': 0.2,
        'addLine': true,
        'width': 2,
        'gradient': false
      }
    }
  },
  'radar': {
    'type': 'radar',
    'color': color,
    'padding': [ 5,5,5,5 ],
    'legend': {
      'show': true,
      'position': 'right',
      'lineHeight': 25
    },
    'extra': {
      'radar': {
        'gridType': 'radar',
        'gridColor': '#CCCCCC',
        'gridCount': 3,
        'opacity': 0.2,
        'labelColor': '#666666',
        'max': 200
      }
    }
  },
  'gauge': {
    'type': 'gauge',
    'color': color,
    'title': {
      'name': '66Km/H',
      'fontSize': 25,
      'color': '#2fc25b',
      'offsetY': 50
    },
    'subtitle': {
      'name': '实时速度',
      'fontSize': 15,
      'color': '#1890ff',
      'offsetY': -50
    },
    'extra': {
      'gauge': {
        'type': 'default',
        'width': 30,
        'labelColor': '#666666',
        'startAngle': 0.75,
        'endAngle': 0.25,
        'startNumber': 0,
        'endNumber': 100,
        'labelFormat': '',
        'splitLine': {
          'fixRadius': 0,
          'splitNumber': 10,
          'width': 30,
          'color': '#FFFFFF',
          'childNumber': 5,
          'childWidth': 12
        },
        'pointer': {
          'width': 24,
          'color': 'auto'
        }
      }
    }
  },
  'candle': {
    'type': 'candle',
    'color': color,
    'padding': [ 15,15,0,15 ],
    'enableScroll': true,
    'enableMarkLine': true,
    'dataLabel': false,
    'xAxis': {
      'labelCount': 4,
      'itemCount': 40,
      'disableGrid': true,
      'gridColor': '#CCCCCC',
      'gridType': 'solid',
      'dashLength': 4,
      'scrollShow': true,
      'scrollAlign': 'left',
      'scrollColor': '#A6A6A6',
      'scrollBackgroundColor': '#EFEBEF'
    },
    'yAxis': {
    },
    'legend': {
    },
    'extra': {
      'candle': {
        'color': {
          'upLine': '#f04864',
          'upFill': '#f04864',
          'downLine': '#2fc25b',
          'downFill': '#2fc25b'
        },
        'average': {
          'show': true,
          'name': [ 'MA5','MA10','MA30' ],
          'day': [ 5,10,20 ],
          'color': [ '#1890ff','#2fc25b','#facc14' ]
        }
      },
      'markLine': {
        'type': 'dash',
        'dashLength': 5,
        'data': [
          {
            'value': 2150,
            'lineColor': '#f04864',
            'showLabel': true
          },
          {
            'value': 2350,
            'lineColor': '#f04864',
            'showLabel': true
          }
        ]
      }
    }
  },
  'mix': {
    'type': 'mix',
    'color': color,
    'padding': [ 15,15,0,15 ],
    'xAxis': {
      'disableGrid': true
    },
    'yAxis': {
      'disabled': false,
      'disableGrid': false,
      'splitNumber': 5,
      'gridType': 'dash',
      'dashLength': 4,
      'gridColor': '#CCCCCC',
      'padding': 10,
      'showTitle': true,
      'data': []
    },
    'legend': {
    },
    'extra': {
      'mix': {
        'column': {
          'width': 20
        }
      }
    }
  },
  'point': {
    'type': 'point',
    'color': color,
    'padding': [ 15,15,0,15 ]
  },
  'bubble': {
    'type': 'bubble',
    'color': color,
    'padding': [ 15,15,0,15 ]
  }
}