# Changelog

All notable changes to this project will be documented in this file. See [standard-version](https://github.com/conventional-changelog/standard-version) for commit guidelines.

## 4.5.5 (2024-10-18)

Manually update version

Details https://github.com/GluuFederation/gluu-passport/issues/574

## [7.0.0](https://github.com/GluuFederation/gluu-passport/compare/v6.0.0...v7.0.0) (2024-08-08)


### ⚠ BREAKING CHANGES

* **saml:** update saml strategy name

### Features

* **passport:** update express ([852f263](https://github.com/GluuFederation/gluu-passport/commit/852f2630677a5b752d9255f0d08b9c64157de6ee))
* **passport:** update express ([d7537d7](https://github.com/GluuFederation/gluu-passport/commit/d7537d71f2451f56ba2dcbc8a1bcc44a99660cb3))
* **passport:** update express ([dee43a7](https://github.com/GluuFederation/gluu-passport/commit/dee43a7731b372bbe35199356a7688e065bed302))
* **passport:** update express ([b75ed78](https://github.com/GluuFederation/gluu-passport/commit/b75ed78dc28a441f64bde41aba1879bd2c13ed0b))
* **saml:** update saml strategy name ([7fc4ee2](https://github.com/GluuFederation/gluu-passport/commit/7fc4ee2d872f298c9eee2488b498599d3ecc5ed3))
* update branch merger ([19ab971](https://github.com/GluuFederation/gluu-passport/commit/19ab971187b1fd50fc2ea41eeec0dcc2cd56b2cb))
* update git flows and add node 20 for test ([6d9863b](https://github.com/GluuFederation/gluu-passport/commit/6d9863b932c778f38a9d533bb084ddc1007e5acf))


### Bug Fixes

* **openid-client:** pass provided scope in auth request ([#539](https://github.com/GluuFederation/gluu-passport/issues/539)) ([68342bd](https://github.com/GluuFederation/gluu-passport/commit/68342bd26663b5f0c7fff35e9497ebec91eede79))
* **package-lock:** update package lock json ([78b6de5](https://github.com/GluuFederation/gluu-passport/commit/78b6de51623c2e82370280a115d8ff26e967e81d))
* **test:** fixing cache provide test cases ([a9f2329](https://github.com/GluuFederation/gluu-passport/commit/a9f2329e4724833b2c5f9376a8c0615e477c7ee1))
* **test:** fixing cache provide test cases ([eef75fb](https://github.com/GluuFederation/gluu-passport/commit/eef75fbe33d5651e16e36305a7047b1d1280f418))
* update branch merger action ([d29cece](https://github.com/GluuFederation/gluu-passport/commit/d29cece95ce066e077c649d3fa4648c232041e8e))
* update jose package fix vulnerabilities ([32f94e7](https://github.com/GluuFederation/gluu-passport/commit/32f94e7e0982ce1c897fdb9ec94f382c026af152))

## [6.0.0](https://github.com/GluuFederation/gluu-passport/compare/v5.4.2...v6.0.0) (2022-12-30)


### ⚠ BREAKING CHANGES

* drop support to node 12 ([#520](https://github.com/GluuFederation/gluu-passport/issues/520))
* remove deprecated passport-oxd
* remove unmantained passport-tumblr ([#517](https://github.com/GluuFederation/gluu-passport/issues/517))

### Bug Fixes

* drop support to node 12 ([#520](https://github.com/GluuFederation/gluu-passport/issues/520)) ([99dbb66](https://github.com/GluuFederation/gluu-passport/commit/99dbb66d9c403ed274a272468bff83565d9ec540))
* remove deprecated passport-oxd ([7625be6](https://github.com/GluuFederation/gluu-passport/commit/7625be60435b3a0115ebefb71a307220441a9ff3)), closes [#516](https://github.com/GluuFederation/gluu-passport/issues/516)
* remove unmantained passport-tumblr ([#517](https://github.com/GluuFederation/gluu-passport/issues/517)) ([04fdda0](https://github.com/GluuFederation/gluu-passport/commit/04fdda08bd38139400cb42ce219843f34e843110)), closes [#515](https://github.com/GluuFederation/gluu-passport/issues/515)

## [5.4.2](https://github.com/GluuFederation/gluu-passport/compare/v5.4.1...v5.4.2) (2022-12-30)


### Bug Fixes

* **security:** bump config to 3.3.8 ([b25c0ba](https://github.com/GluuFederation/gluu-passport/commit/b25c0ba16976d8c89427dd34883686bf0204e8c1))
* **security:** bump got to 11.8.6 ([d38356d](https://github.com/GluuFederation/gluu-passport/commit/d38356dbd77b9e74074679e4d4aea0a40542229c))
* **security:** bump jose to 4.11.1 ([04f5fd4](https://github.com/GluuFederation/gluu-passport/commit/04f5fd40bee25920469799d7d27e09a7a0be9cb6))
* **security:** bump jsonwebtoken from 8.5.1 to 9.0.0 ([#511](https://github.com/GluuFederation/gluu-passport/issues/511)) ([a505439](https://github.com/GluuFederation/gluu-passport/commit/a505439a0709dbd073a619ecbd3aff38a84850da))
* **security:** bump moment from 2.29.3 to 2.29.4 ([#483](https://github.com/GluuFederation/gluu-passport/issues/483)) ([77298eb](https://github.com/GluuFederation/gluu-passport/commit/77298eb1df4e916b44eb6135c64ca0e28add3ef9))
* **security:** bump passport to 0.6.0 ([5cd14ec](https://github.com/GluuFederation/gluu-passport/commit/5cd14ecd6a0d9d7b3d87ab44308f9555aa4df4fa))
* **security:** bump passport-apple to v2 ([04a00cf](https://github.com/GluuFederation/gluu-passport/commit/04a00cfc60112fe13d5018f3632d655b76a19af6))
* **security:** bump winston ([5a65ca5](https://github.com/GluuFederation/gluu-passport/commit/5a65ca5e88dbed8a42127ae6b271ab5162898bae))
* **security:** update passport twitter xmldom dep ([2257e69](https://github.com/GluuFederation/gluu-passport/commit/2257e69c2e2aae4226b282b6164c6d786f31d04c))

## [5.4.1](https://github.com/GluuFederation/gluu-passport/compare/v5.4.0...v5.4.1) (2022-12-05)


### Bug Fixes

* **security:** bump passport-saml from 3.2.1 to 3.2.4 ([ac8b62b](https://github.com/GluuFederation/gluu-passport/commit/ac8b62b0402870e263ad4e5d79076c48e2003b38))

## [5.4.0](https://github.com/GluuFederation/gluu-passport/compare/v5.3.2...v5.4.0) (2022-06-22)


### Features

* **production.js:** rate limit config from env ([#452](https://github.com/GluuFederation/gluu-passport/issues/452)) ([067b1a7](https://github.com/GluuFederation/gluu-passport/commit/067b1a7c9781a57fee5d520773f5b58a08429531))

## [5.3.2](https://github.com/GluuFederation/gluu-passport/compare/v5.2.0...v5.3.2) (2022-06-15)


### Features

* **httpProxy:** add support to global http proxy ([#333](https://github.com/GluuFederation/gluu-passport/issues/333)) ([121a629](https://github.com/GluuFederation/gluu-passport/commit/121a629189e36d7b500ea10d82459d6906cda030))


### Bug Fixes

* change log level to `debug` as `silly` is not used ([#348](https://github.com/GluuFederation/gluu-passport/issues/348)) ([228ae5f](https://github.com/GluuFederation/gluu-passport/commit/228ae5fdf75a498be2c193565e0f0c91b7d4da1c))
* **loggin.js:** add winston patch to fix date problem ([#364](https://github.com/GluuFederation/gluu-passport/issues/364)) ([fdf14ca](https://github.com/GluuFederation/gluu-passport/commit/fdf14ca7389f10383ecf2428a01feb20cb3c0096))
* **package-lock.json:** update node-xtraverse due to vulnerability in xmldom ([#327](https://github.com/GluuFederation/gluu-passport/issues/327)) ([903ebb4](https://github.com/GluuFederation/gluu-passport/commit/903ebb4b9e5900496c883d5e5e1f72fb39992a32))
* remove decimal points from Client assertion JWT exp time ([#315](https://github.com/GluuFederation/gluu-passport/issues/315)) ([6ba5b9b](https://github.com/GluuFederation/gluu-passport/commit/6ba5b9bc687e1cdd7cc17fb22053e659f961282b)), closes [#313](https://github.com/GluuFederation/gluu-passport/issues/313)
* **security:** update deps for passport-oauth2 ([#420](https://github.com/GluuFederation/gluu-passport/issues/420)) ([d61566e](https://github.com/GluuFederation/gluu-passport/commit/d61566e50902f4dbe237a345dd3f402a17de681e))
* **sp-meta.spec.js:** fix saml test case ([b3cb6b2](https://github.com/GluuFederation/gluu-passport/commit/b3cb6b26bdf9cbf44fe255fd20a62c89e009f4e8))


### Miscellaneous Chores

* release 5.3.2 ([#447](https://github.com/GluuFederation/gluu-passport/issues/447)) ([141e683](https://github.com/GluuFederation/gluu-passport/commit/141e683fd17d348932d99ebf3d28c8cf86e3628e))

### [5.3.1](https://github.com/GluuFederation/gluu-passport/compare/v5.3.0...v5.3.1) (2021-10-06)


### Bug Fixes

* change log level to `debug` as `silly` is not used ([#348](https://github.com/GluuFederation/gluu-passport/issues/348)) ([228ae5f](https://github.com/GluuFederation/gluu-passport/commit/228ae5fdf75a498be2c193565e0f0c91b7d4da1c))
* **sp-meta.spec.js:** fix saml test case ([b3cb6b2](https://github.com/GluuFederation/gluu-passport/commit/b3cb6b26bdf9cbf44fe255fd20a62c89e009f4e8))

## [5.3.0](https://github.com/GluuFederation/gluu-passport/compare/v5.2.0...v5.3.0) (2021-09-03)


### Features

* **httpProxy:** add support to global http proxy ([#333](https://github.com/GluuFederation/gluu-passport/issues/333)) ([121a629](https://github.com/GluuFederation/gluu-passport/commit/121a629189e36d7b500ea10d82459d6906cda030))


### Bug Fixes

* **package-lock.json:** update node-xtraverse due to vulnerability in xmldom ([#327](https://github.com/GluuFederation/gluu-passport/issues/327)) ([903ebb4](https://github.com/GluuFederation/gluu-passport/commit/903ebb4b9e5900496c883d5e5e1f72fb39992a32))
* remove decimal points from Client assertion JWT exp time ([#315](https://github.com/GluuFederation/gluu-passport/issues/315)) ([6ba5b9b](https://github.com/GluuFederation/gluu-passport/commit/6ba5b9bc687e1cdd7cc17fb22053e659f961282b)), closes [#313](https://github.com/GluuFederation/gluu-passport/issues/313) [#313](https://github.com/GluuFederation/gluu-passport/issues/313) [#313](https://github.com/GluuFederation/gluu-passport/issues/313)

### [5.2.1](https://github.com/GluuFederation/gluu-passport/compare/v5.2.0...v5.2.1) (2021-07-30)


### Bug Fixes

* remove decimal points from Client assertion JWT exp time ([#315](https://github.com/GluuFederation/gluu-passport/issues/315)) ([6ba5b9b](https://github.com/GluuFederation/gluu-passport/commit/6ba5b9bc687e1cdd7cc17fb22053e659f961282b)), closes [#313](https://github.com/GluuFederation/gluu-passport/issues/313) [#313](https://github.com/GluuFederation/gluu-passport/issues/313) [#313](https://github.com/GluuFederation/gluu-passport/issues/313)

## [5.2.0](https://github.com/GluuFederation/gluu-passport/compare/v5.1.0...v5.2.0) (2021-07-15)


### Features

* **openid-client-helper.js:** init issuer with and without discovery endpoint ([c287f10](https://github.com/GluuFederation/gluu-passport/commit/c287f10da56ab7e1788bf79e5d4b5735a2ff9c0b)), closes [#241](https://github.com/GluuFederation/gluu-passport/issues/241)


### Bug Fixes

* **logging.test.js:** fix empty dirname and use existing log dir path ([003cf15](https://github.com/GluuFederation/gluu-passport/commit/003cf15d9b222755268dd0ff3a23e7f9933a82af)), closes [#297](https://github.com/GluuFederation/gluu-passport/issues/297)

## [5.1.0](https://github.com/GluuFederation/gluu-passport/compare/v5.0.0...v5.1.0) (2021-07-13)


### Features

* **routes.js:** track error logs generated by passport strategies ([9820226](https://github.com/GluuFederation/gluu-passport/commit/98202261329c8b4137173777b918ed7416ce5de2)), closes [#250](https://github.com/GluuFederation/gluu-passport/issues/250)


### Bug Fixes

* **dependencies:** relock file to fix vulnerability ([d5c298f](https://github.com/GluuFederation/gluu-passport/commit/d5c298f32890689cf073390a17c1205d134cc59c))
* **package.json:** fix twitter volunerability ([1812efd](https://github.com/GluuFederation/gluu-passport/commit/1812efd99f0e0fe6f297cc1ddf5d054d825801d3)), closes [#265](https://github.com/GluuFederation/gluu-passport/issues/265)
* **package.json:** fix url issue ([93dde9a](https://github.com/GluuFederation/gluu-passport/commit/93dde9a640847115161a433a3f8ff95d695d576d))
* **security:** update vulnerable dep tree ([59b725a](https://github.com/GluuFederation/gluu-passport/commit/59b725abf756d8e959a0c454ece26db78de97124))

## [5.0.0](https://github.com/GluuFederation/gluu-passport/compare/v4.4.0...v5.0.0) (2021-06-03)


### ⚠ BREAKING CHANGES

* **openid-connect:** All openid providers needs to be reconfigured according to new specs. Changes
required at oxTrust.
* **security:** We need to update from ox trust breakingchnage descriptiotn

### Features

* **app-factory:** ensure session is handlable externaly ([e7106e6](https://github.com/GluuFederation/gluu-passport/commit/e7106e6545b422a149b457c96110a9ecc10ad1e6))
* **config:** ensure sameSite exists and value is lax in default ([aeff791](https://github.com/GluuFederation/gluu-passport/commit/aeff791b6d68add2e33da13d204592c28f903cc5))
* **config:** ensure sameSite exists in production ([3ee1504](https://github.com/GluuFederation/gluu-passport/commit/3ee1504c2fd4aa1079ea5de99440d0576e8e38e9))
* **config:** ensure secure exists in production ([e3d41c3](https://github.com/GluuFederation/gluu-passport/commit/e3d41c37d0e6ae4da26e5aab303c2a2b6040b647))
* **config:** ensure secure is true in production ([5747cba](https://github.com/GluuFederation/gluu-passport/commit/5747cbaf9497d99363cda519705e82b5281d67dd))
* **config:** ensure secure value is false in default ([9709ab9](https://github.com/GluuFederation/gluu-passport/commit/9709ab9a7c398bea4ae6e8ce21aa04cbc3a65121))
* **file-utils.js file-utils.test.js:** made a seperate utils for common file operations ([fbdf323](https://github.com/GluuFederation/gluu-passport/commit/fbdf32319527c91b5dc7c280a22b3cac50855a1d)), closes [#206](https://github.com/GluuFederation/gluu-passport/issues/206)
* **openid-client-helper.js:** Added utility for openid client strategy initialization ([da25ac3](https://github.com/GluuFederation/gluu-passport/commit/da25ac39040bb10e0f8f9ac73f42205cdb4da277)), closes [#206](https://github.com/GluuFederation/gluu-passport/issues/206)
* **openid-connect:** replace deprecated openid lib with openid-client ([e0e165f](https://github.com/GluuFederation/gluu-passport/commit/e0e165fbafdd9ad7074051af5b84d373c00c8756)), closes [#204](https://github.com/GluuFederation/gluu-passport/issues/204)
* **package.json:** support node 14.16.0 to 15.11.0 ([da7fd3b](https://github.com/GluuFederation/gluu-passport/commit/da7fd3be36c65ff01d675fb9e0d37e3971c54589)), closes [#213](https://github.com/GluuFederation/gluu-passport/issues/213)
* **providers.js:** added openid-client strategy support ([514aad8](https://github.com/GluuFederation/gluu-passport/commit/514aad8dac91b74ce29b9b23051b91b4542d0e22)), closes [#206](https://github.com/GluuFederation/gluu-passport/issues/206)
* **session.js session.test.js:** make separate file for session config ([5092baf](https://github.com/GluuFederation/gluu-passport/commit/5092bafe75a6240f095c1aff5d84f4521383a084)), closes [#242](https://github.com/GluuFederation/gluu-passport/issues/242)


### Bug Fixes

* **config.test.js:** update cookie config keys title ([72646a5](https://github.com/GluuFederation/gluu-passport/commit/72646a5600a68e3229f6faba60f126e776f6b597)), closes [#242](https://github.com/GluuFederation/gluu-passport/issues/242)
* **security:** reokacc openid connect dep ([224fbdd](https://github.com/GluuFederation/gluu-passport/commit/224fbdd44a50caab49d17cf0801b506bcfac4e70))
* **session:** ensure cookies settings are correct ([7c24b83](https://github.com/GluuFederation/gluu-passport/commit/7c24b835c0580aa3a09c16486edb9045d88eca36))
* package.json & package-lock.json to reduce vulnerabilities ([218b7ce](https://github.com/GluuFederation/gluu-passport/commit/218b7cef7f8b2cf465d0b059246fabc3da4458e4))

## [4.5.1](https://github.com/GluuFederation/gluu-passport/compare/v4.4.0...v4.5.1) (2021-03-30)


### Features

* **config:** ensure sameSite exists and value is lax in default ([aeff791](https://github.com/GluuFederation/gluu-passport/commit/aeff791b6d68add2e33da13d204592c28f903cc5))
* **config:** ensure sameSite exists in production ([3ee1504](https://github.com/GluuFederation/gluu-passport/commit/3ee1504c2fd4aa1079ea5de99440d0576e8e38e9))
* **config:** ensure secure exists in production ([e3d41c3](https://github.com/GluuFederation/gluu-passport/commit/e3d41c37d0e6ae4da26e5aab303c2a2b6040b647))
* **config:** ensure secure is true in production ([5747cba](https://github.com/GluuFederation/gluu-passport/commit/5747cbaf9497d99363cda519705e82b5281d67dd))
* **config:** ensure secure value is false in default ([9709ab9](https://github.com/GluuFederation/gluu-passport/commit/9709ab9a7c398bea4ae6e8ce21aa04cbc3a65121))
* **session** make separate file for session config ([5092baf](https://github.com/GluuFederation/gluu-passport/commit/5092bafe75a6240f095c1aff5d84f4521383a084)), closes [#242](https://github.com/GluuFederation/gluu-passport/issues/242)


### Bug Fixes

* **config.test.js:** update cookie config keys title ([72646a5](https://github.com/GluuFederation/gluu-passport/commit/72646a5600a68e3229f6faba60f126e776f6b597)), closes [#242](https://github.com/GluuFederation/gluu-passport/issues/242)
* **session:** ensure cookies settings are correct ([7c24b83](https://github.com/GluuFederation/gluu-passport/commit/7c24b835c0580aa3a09c16486edb9045d88eca36))
* package.json & package-lock.json to reduce vulnerabilities ([218b7ce](https://github.com/GluuFederation/gluu-passport/commit/218b7cef7f8b2cf465d0b059246fabc3da4458e4))

## [4.4.0](https://github.com/GluuFederation/gluu-passport/compare/v4.3.6...v4.4.0) (2021-02-22)


### Features

* **app-factory.js:** added rate-limiting facility ([51b6ba3](https://github.com/GluuFederation/gluu-passport/commit/51b6ba3c5da71a955ecef232d4e6c2cfa8c3c2e5)), closes [#139](https://github.com/GluuFederation/gluu-passport/issues/139)


### Bug Fixes

* **app:** add csrf middleware ([ef71ec4](https://github.com/GluuFederation/gluu-passport/commit/ef71ec434c54a5ce32a3aea87a83558e13bbdfb0)), closes [#140](https://github.com/GluuFederation/gluu-passport/issues/140)
* **app:** generate random secret for session middleware ([c6202ad](https://github.com/GluuFederation/gluu-passport/commit/c6202ad464f2f1b311c965d28b5db49b5078ac49)), closes [#144](https://github.com/GluuFederation/gluu-passport/issues/144)
* **app-factory.js:** add missing parenthesis to randomSecret() ([2ff8a29](https://github.com/GluuFederation/gluu-passport/commit/2ff8a29f35382eedb1560d324f8306fed8effd27))
* **app-factory.js:** fix location undefine and req.flash function problem ([6d10f9b](https://github.com/GluuFederation/gluu-passport/commit/6d10f9ba8be788ef4265c550b82996bfd0872000)), closes [#170](https://github.com/GluuFederation/gluu-passport/issues/170) [#173](https://github.com/GluuFederation/gluu-passport/issues/173)
* **app-factory.js:** remove undeeded csurf middleware ([2b2152f](https://github.com/GluuFederation/gluu-passport/commit/2b2152f6483121dbb46b4dbda022e05de4aeb4e7)), closes [#169](https://github.com/GluuFederation/gluu-passport/issues/169)
* **husky:** add missing .huskyrc.json ([990ce91](https://github.com/GluuFederation/gluu-passport/commit/990ce911bb442c4c9fdfdc5aaec4fc42e1851a45))
* **logging.js:** add propper code for assigning empty string to msg ([9846f23](https://github.com/GluuFederation/gluu-passport/commit/9846f2314592463ceeae23a8b6bf45dc647df6bd))
* **routes.js:** remove metadata input name on outgoing request ([1738306](https://github.com/GluuFederation/gluu-passport/commit/1738306ec44daf5e3e5a0b31852a68149f63071e)), closes [#137](https://github.com/GluuFederation/gluu-passport/issues/137)
* **routes.js:** remove provider name from error message to avoid cross script ([577daaa](https://github.com/GluuFederation/gluu-passport/commit/577daaacefbb10075fbdd2b5753c7e76f90b418c)), closes [#137](https://github.com/GluuFederation/gluu-passport/issues/137)
* **routes.js:** remove received input from error output msg ([4c7f204](https://github.com/GluuFederation/gluu-passport/commit/4c7f2044afbb7a61ada9b17ded9baad80c24ccbe)), closes [#137](https://github.com/GluuFederation/gluu-passport/issues/137)
* **uma.js:** fixed form data send problem ([478b452](https://github.com/GluuFederation/gluu-passport/commit/478b4528ed793f22b87371b985d7a9f448269101)), closes [#205](https://github.com/GluuFederation/gluu-passport/issues/205)

### [4.3.8](https://github.com/GluuFederation/gluu-passport/compare/v4.3.7...v4.3.8) (2020-12-10)


### Bug Fixes

* **app-factory.js:** fix location undefine and req.flash function problem ([6d10f9b](https://github.com/GluuFederation/gluu-passport/commit/6d10f9ba8be788ef4265c550b82996bfd0872000)), closes [#170](https://github.com/GluuFederation/gluu-passport/issues/170) [#173](https://github.com/GluuFederation/gluu-passport/issues/173)
* **app-factory.js:** remove undeeded csurf middleware ([2b2152f](https://github.com/GluuFederation/gluu-passport/commit/2b2152f6483121dbb46b4dbda022e05de4aeb4e7)), closes [#169](https://github.com/GluuFederation/gluu-passport/issues/169)

### [4.3.7](https://github.com/GluuFederation/gluu-passport/compare/v4.3.6...v4.3.7) (2020-11-25)


### Bug Fixes

* **app:** add csrf middleware ([ef71ec4](https://github.com/GluuFederation/gluu-passport/commit/ef71ec434c54a5ce32a3aea87a83558e13bbdfb0)), closes [#140](https://github.com/GluuFederation/gluu-passport/issues/140)
* **app:** generate random secret for session middleware ([c6202ad](https://github.com/GluuFederation/gluu-passport/commit/c6202ad464f2f1b311c965d28b5db49b5078ac49)), closes [#144](https://github.com/GluuFederation/gluu-passport/issues/144)
* **app-factory.js:** add missing parenthesis to randomSecret() ([2ff8a29](https://github.com/GluuFederation/gluu-passport/commit/2ff8a29f35382eedb1560d324f8306fed8effd27))
* **husky:** add missing .huskyrc.json ([990ce91](https://github.com/GluuFederation/gluu-passport/commit/990ce911bb442c4c9fdfdc5aaec4fc42e1851a45))
* **logging.js:** add propper code for assigning empty string to msg ([9846f23](https://github.com/GluuFederation/gluu-passport/commit/9846f2314592463ceeae23a8b6bf45dc647df6bd))
* **routes.js:** remove metadata input name on outgoing request ([1738306](https://github.com/GluuFederation/gluu-passport/commit/1738306ec44daf5e3e5a0b31852a68149f63071e)), closes [#137](https://github.com/GluuFederation/gluu-passport/issues/137)
* **routes.js:** remove provider name from error message to avoid cross script ([577daaa](https://github.com/GluuFederation/gluu-passport/commit/577daaacefbb10075fbdd2b5753c7e76f90b418c)), closes [#137](https://github.com/GluuFederation/gluu-passport/issues/137)
* **routes.js:** remove received input from error output msg ([4c7f204](https://github.com/GluuFederation/gluu-passport/commit/4c7f2044afbb7a61ada9b17ded9baad80c24ccbe)), closes [#137](https://github.com/GluuFederation/gluu-passport/issues/137)

### [4.3.6](https://github.com/GluuFederation/gluu-passport/compare/v4.3.5...v4.3.6) (2020-10-31)

### Bug Fixes

* **routes.js:** add extended option to urlencode function call ([74ae36c](https://github.com/GluuFederation/gluu-passport/commit/74ae36ca692423130bab5adccc7222c7fd8dc2b1)), closes [#126](https://github.com/GluuFederation/gluu-passport/issues/126)
* solved the provider update strategy problem [#119](https://github.com/GluuFederation/gluu-passport/issues/119) ([3c4f725](https://github.com/GluuFederation/gluu-passport/commit/3c4f725003d2bfec1f8677b51b19b6ff01b512a5))
