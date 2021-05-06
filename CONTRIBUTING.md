
## <a name="commit"></a> Commit Message Format

Any line of the commit message cannot be longer than 100 characters.


#### Commit Message Header

```
<type>(<scope>): <short summary>
  │       │             │
  │       │             └─⫸ Summary in present tense. Not capitalized. No period at the end.
  │       │
  │       └─⫸ Commit Scope: common|discovery-server|gateway|question-service|answer-service|vote-service|...
  │
  └─⫸ Commit Type: docs|feat|fix|perf|refactor|test|build|ci|...
```

The `<type>` and `<summary>` fields are mandatory, the `(<scope>)` field is optional.


##### Type

Must be one of the following:

* **docs**: Documentation only changes
* **feat**: A new feature
* **fix**: A bug fix
* **perf**: A code change that improves performance
* **refactor**: A code change that neither fixes a bug nor adds a feature
* **test**: Adding missing tests or correcting existing tests
* **build**: Changes that affect the build system or external dependencies (example scopes: gulp, broccoli, npm)
* **ci**: Changes to our CI configuration files and scripts (example scopes: Circle, BrowserStack, SauceLabs)


##### Scope
The scope may be an specific micro-service or if relavent to the whole project `common` can be used.


*Based on [angular/angular](https://github.com/angular/angular/blob/master/CONTRIBUTING.md#commit-message-header) contributing guidelines.*
