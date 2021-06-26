// This file can be replaced during build by using the `fileReplacements` array.
// `ng build --prod` replaces `environment.ts` with `environment.prod.ts`.
// The list of file replacements can be found in `angular.json`.

const API_GATETEWAY = 'http://localhost:8080/api';

export const environment = {
  production: false,
  API_GATETEWAY_SERVICE: API_GATETEWAY,
  API_USER_SERVICE: `${API_GATETEWAY}/user-service`,
  API_QUESTION_SERVICE: `${API_GATETEWAY}/question-service`,
  API_ANSWER_SERVICE: `${API_GATETEWAY}/answer-service`,
  API_VOTE_SERVICE: `${API_GATETEWAY}/vote-service`,
};

/*
 * For easier debugging in development mode, you can import the following file
 * to ignore zone related error stack frames such as `zone.run`, `zoneDelegate.invokeTask`.
 *
 * This import should be commented out in production mode because it will have a negative impact
 * on performance if an error is thrown.
 */
// import 'zone.js/dist/zone-error';  // Included with Angular CLI.
