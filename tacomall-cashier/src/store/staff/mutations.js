
import * as types from './mutation-types';

export const mutations = {
  [types.SET_INFO] (state, o) {
    state.info = o;
  }
};
