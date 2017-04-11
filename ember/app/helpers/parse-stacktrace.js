import Ember from 'ember';

export function parseStacktrace(params) {
  return JSON.parse(params[0]).map((stackElement => JSON.stringify(stacktrace[stackElement]).replace(new RegExp(',', 'g'), ', ')));
}

export default Ember.Helper.helper(parseStacktrace);
