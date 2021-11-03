module.exports = {
  root: true,
  env: {
    browser: true,
    es6: true,
    node: true
  },
  extends: 'standard',
  globals: {
    Atomics: 'readonly',
    SharedArrayBuffer: 'readonly',
    Vue: 'readonly',
    AMap: true
  },
  parserOptions: {
    ecmaVersion: 2018,
    sourceType: 'module',
    parser: 'babel-eslint'
  },
  parser: 'vue-eslint-parser',
  plugins: [
    'vue',
    'babel'
  ],
  // overrides: [
  //   {
  //     files: [
  //       '**/__tests__/*.{j,t}s?(x)',
  //       '**/tests/unit/**/*.spec.{j,t}s?(x)'
  //     ],
  //     env: {
  //       jest: true
  //     }
  //   }
  // ],
  rules: {
    indent: [
      'error',
      2,
      {
        VariableDeclarator: 'first',
        ObjectExpression: 1,
        SwitchCase: 1,
        flatTernaryExpressions: false
      }
    ],
    'linebreak-style': ['error', 'windows'],
    'no-unused-vars': ['error'],
    quotes: ['error', 'single'],
    semi: ['error', 'always'],
    'space-before-function-paren': [
      'error',
      {
        anonymous: 'always',
        named: 'always',
        asyncArrow: 'always'
      }
    ],
    'key-spacing': ['error', { beforeColon: false }],
    'no-trailing-spaces': 'error',
    'comma-spacing': ['error', { before: false, after: true }],
    'comma-dangle': ['error', 'never'],
    'object-curly-spacing': ['error', 'always'],
    eqeqeq: 0,
    // vue
    'vue/html-indent': ['error', 2, {
      attribute: 1,
      baseIndent: 1,
      closeBracket: 0,
      alignAttributesVertically: true,
      ignores: []
    }],
    'vue/component-definition-name-casing': ['error', 'PascalCase'],
    'vue/attribute-hyphenation': ['error', 'always', { ignore: [] }],
    'vue/html-closing-bracket-newline': ['error', {
      singleline: 'never',
      multiline: 'never'
    }],
    'vue/mustache-interpolation-spacing': ['error', 'always'],
    'vue/no-multi-spaces': ['error', { ignoreProperties: true }],
    'vue/no-spaces-around-equal-signs-in-attribute': ['error'],
    'vue/component-name-in-template-casing': ['error', 'kebab-case', {
      registeredComponentsOnly: true,
      ignores: []
    }]
  }
};
