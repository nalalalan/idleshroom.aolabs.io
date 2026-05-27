# Idle Shroom Google Play Launch

Current Play status: Android packaging is prepared from the standalone `idleshroom.aolabs.io` repo. Production release is blocked until the Play Console app, signing, review forms, screenshots, AdMob IDs, and ad-spend approval exist.

## App Identity

- App name: Idle Shroom
- Package name: `io.aolabs.idleshroom`
- Category: Game / Casual
- Default language: English (United States)
- Developer contact: `alan@aolabs.io`
- Website: `https://aolabs.io/mushroom-boop/`
- Privacy policy: `http://idleshroom.aolabs.io/privacy.html` until HTTPS is issued, then `https://idleshroom.aolabs.io/privacy.html`

## Store Listing Draft

Short description:

Tap enemies, grow shroom allies, mutate your colony, and Spore Bloom stronger.

Full description:

Idle Shroom is a cozy-dark mushroom idle clicker RPG about starting as the First Shroom and growing a living fungal colony around it.

Tap enemies for Nutrients, grow shroom allies, catch rare spawns, defeat oversized forest threats, upgrade relics, unlock mutation paths, and climb the spore record. The game keeps changing as you play: new zones, richer colony art, ally milestones, boss walls, and Spore Bloom resets that make the next run stronger.

Idle Shroom is designed for quick check-ins and long idle sessions:

- Tap enemies for spore bursts.
- Grow Button Buddies, Cap Knights, Puffball Bombers, Glowshroom Mages, Truffle Miners, and ancient fungal allies.
- Push through forest, log, cavern, swamp, ruin, and deep mycelium zones.
- Upgrade relics with Relic Caps and activate mutations with Mutation Goo.
- Spore Bloom to reset with permanent Ancient Spores.
- Submit your run to the leaderboard.
- Watch optional rewarded ads for temporary Nutrient Frenzy boosts after ads are configured.

No account is required. Progress is saved on your device.

## First Release Notes

Idle Shroom launches with the full cozy-dark clicker loop: tap combat, Nutrients, shroom allies, active skills, rare spawns, daily goals, relic upgrades, mutation paths, Spore Bloom prestige, offline progress, and long-play milestones through absurd scaling numbers.

## Required Play Console Steps

1. Create the app in Play Console with package `io.aolabs.idleshroom`.
2. Complete store listing, app category, contact details, privacy policy, content rating, target audience, ads declaration, data safety, and app access forms.
3. Create an internal test release first.
4. Upload a signed Android App Bundle (`.aab`).
5. If the Play developer account is a new personal account, run the required closed test before production access.
6. Only after the app is approved, create Google Ads App campaign assets and budget.

## Ad Launch Plan

Monetization ads:

- Use AdMob for Android in-app ads.
- Native rewarded ads are wired through `MushroomBoopRewardedAdPlugin`.
- Keep banner ads optional and low-pressure; do not add automatic interstitials to the tap loop.
- Use rewarded ads for the Nutrient Frenzy boost only after the user taps the boost button.
- Use Google test IDs during development only.
- Replace `android/app/src/main/res/values/strings.xml` `admob_app_id` and `ads-config.js` `admob.androidAppId` / `admob.rewardedUnitId` with real AdMob IDs only after the app exists in AdMob and Play Console.
- Leave `ads-config.js` blank for public web until AdSense/AdMob approvals exist.

Acquisition ads:

- Do not start paid Google Ads until the Play listing is approved or at least available for pre-registration/internal testing.
- Initial campaign: App promotion / installs, low daily cap, United States only, broad casual-idle-game creative.
- Creative assets needed: app icon, 3-5 screenshots, one 10-30 second vertical gameplay video, 5 short text lines, 5 headline variants.

## Current Blockers

- No local Java/Android SDK on this Windows machine, so a release `.aab` cannot be built here yet.
- No Android upload signing key has been created or stored.
- No Play Console access in this Codex session.
- No AdMob app ID or ad unit IDs configured.
- No Google Ads billing/budget approval.
- `https://idleshroom.aolabs.io` certificate is still pending; use the `aolabs.io/mushroom-boop` HTTPS route until it is issued.

## Build Commands

```bash
npm install
npm run build:web
npx cap sync android
npm run android:bundle
```

`npm run android:bundle` requires a local JDK and Android SDK. The current machine stops at `JAVA_HOME is not set and no 'java' command could be found in your PATH.`

## GitHub Signing Secrets

The Android bundle workflow can produce a signed Play upload when these repository secrets exist:

- `ANDROID_KEYSTORE_BASE64`: base64-encoded upload key `.jks`
- `ANDROID_KEYSTORE_PASSWORD`
- `ANDROID_KEY_ALIAS`
- `ANDROID_KEY_PASSWORD`

Without those secrets, the workflow still proves the Android project builds, but the downloaded `.aab` is not a final Play upload artifact.
