import Ember from 'ember';

export default Ember.Route.extend({
  ajax: Ember.inject.service(),
  model(params) {
    return Ember.RSVP.hash({
      locations: this.get('ajax').request('/getAllCities'),
    });
  },
});
