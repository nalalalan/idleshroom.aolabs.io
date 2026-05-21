const fs = require("fs");
const path = require("path");

const root = path.resolve(__dirname, "..");
const out = path.join(root, "www");

const files = [
  ".nojekyll",
  "about.html",
  "ads-config.js",
  "ads.js",
  "game.js",
  "icon.svg",
  "index.html",
  "manifest.webmanifest",
  "online-config.js",
  "privacy.html",
  "robots.txt",
  "service-worker.js",
  "sitemap.xml",
  "styles.css",
  "suite-header.css"
];

const dirs = ["icons", "marks"];

function copyFile(relativePath) {
  const src = path.join(root, relativePath);
  const dest = path.join(out, relativePath);
  if (!fs.existsSync(src)) return;
  fs.mkdirSync(path.dirname(dest), { recursive: true });
  fs.copyFileSync(src, dest);
}

function copyDir(relativePath) {
  const src = path.join(root, relativePath);
  const dest = path.join(out, relativePath);
  if (!fs.existsSync(src)) return;
  fs.cpSync(src, dest, { recursive: true });
}

fs.rmSync(out, { recursive: true, force: true });
fs.mkdirSync(out, { recursive: true });
files.forEach(copyFile);
dirs.forEach(copyDir);

console.log(`Built Idle Shroom web assets into ${out}`);

