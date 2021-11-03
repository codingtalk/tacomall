/*
 * @Author: 码上talk|RC
 * @Date: 2021-05-05 08:09:30
 * @LastEditTime: 2021-05-07 08:11:30
 * @LastEditors: 码上talk|RC
 * @Description: 
 * @FilePath: /web-admin_v2/tests/unit/formValidateFieldspec.js
 * @微信:  13680065830
 * @邮箱:  3189482282@qq.com
 * @oops: Just do what I think it is right
 */

import { shallowMount } from '@vue/test-utils';
import formValidateField from '@/components/form-validate/field.vue';

describe('formValidateField.vue', () => {
  it('renders props.msg when passed', () => {
    const label = 'label text';
    const wrapper = shallowMount(formValidateField, {
      propsData: { label }
    });
    expect(wrapper.text()).toMatch(label);
  });
});
