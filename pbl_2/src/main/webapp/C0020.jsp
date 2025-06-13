<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
  <head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="msapplication-tap-highlight" content="no">
    <meta name="description" content="">
    <title>売上管理</title>
    <link href="https://cdn.datatables.net/v/dt/dt-1.10.16/datatables.min.css" rel="stylesheet">
    <link href="//themes.materializecss.com/cdn/shop/t/1/assets/jqvmap.css?v=162757563705857184351528499283" rel="stylesheet">
    <link href="//themes.materializecss.com/cdn/shop/t/1/assets/flag-icon.min.css?v=107574258948483483761528499307" rel="stylesheet">
    <!-- Fullcalendar-->
    <link href="https://cdnjs.cloudflare.com/ajax/libs/fullcalendar/3.7.0/fullcalendar.min.css" rel="stylesheet">
    <!-- Materialize-->
    <link href="//themes.materializecss.com/cdn/shop/t/1/assets/admin-materialize.min.css?v=88505356707424191531528497992" rel="stylesheet">
    <!-- Material Icons-->
    <link href="https://fonts.googleapis.com/icon?family=Material+Icons" rel="stylesheet">

    <script>window.performance && window.performance.mark && window.performance.mark('shopify.content_for_header.start');</script><meta id="shopify-digital-wallet" name="shopify-digital-wallet" content="/17758583/digital_wallets/dialog">
<meta name="shopify-checkout-api-token" content="6aacc581eb2b41d74f03c38d3c985dba">
<meta id="in-context-paypal-metadata" data-shop-id="17758583" data-venmo-supported="false" data-environment="production" data-locale="en_US" data-paypal-v4="true" data-currency="JPY">
<script async="async" src="/checkouts/internal/preloads.js?locale=en-JP"></script>
<link rel="preconnect" href="https://shop.app" crossorigin="anonymous">
<script async="async" src="https://shop.app/checkouts/internal/preloads.js?locale=en-JP&shop_id=17758583" crossorigin="anonymous"></script>
<script id="apple-pay-shop-capabilities" type="application/json">{"shopId":17758583,"countryCode":"US","currencyCode":"JPY","merchantCapabilities":["supports3DS"],"merchantId":"gid:\/\/shopify\/Shop\/17758583","merchantName":"Materialize Themes","requiredBillingContactFields":["postalAddress","email"],"requiredShippingContactFields":["postalAddress","email"],"shippingType":"shipping","supportedNetworks":["visa","masterCard","amex","discover","elo","jcb"],"total":{"type":"pending","label":"Materialize Themes","amount":"1.00"},"shopifyPaymentsEnabled":true,"supportsSubscriptions":true}</script>
<script id="shopify-features" type="application/json">{"accessToken":"6aacc581eb2b41d74f03c38d3c985dba","betas":["rich-media-storefront-analytics"],"domain":"themes.materializecss.com","predictiveSearch":true,"shopId":17758583,"locale":"en"}</script>
<script>var Shopify = Shopify || {};
Shopify.shop = "materialize-themes.myshopify.com";
Shopify.locale = "en";
Shopify.currency = {"active":"JPY","rate":"146.039215"};
Shopify.country = "JP";
Shopify.theme = {"name":"debut","id":133945025,"schema_name":"Debut","schema_version":"1.0.1","theme_store_id":796,"role":"main"};
Shopify.theme.handle = "null";
Shopify.theme.style = {"id":null,"handle":null};
Shopify.cdnHost = "themes.materializecss.com/cdn";
Shopify.routes = Shopify.routes || {};
Shopify.routes.root = "/";</script>
<script type="module">!function(o){(o.Shopify=o.Shopify||{}).modules=!0}(window);</script>
<script>!function(o){function n(){var o=[];function n(){o.push(Array.prototype.slice.apply(arguments))}return n.q=o,n}var t=o.Shopify=o.Shopify||{};t.loadFeatures=n(),t.autoloadFeatures=n()}(window);</script>
<script>window.ShopifyPay = window.ShopifyPay || {};
window.ShopifyPay.apiHost = "shop.app\/pay";</script>
<script id="shop-js-analytics" type="application/json">{"pageType":"page"}</script>
<script>
  window.Shopify = window.Shopify || {};
  if (!window.Shopify.featureAssets) window.Shopify.featureAssets = {};
  window.Shopify.featureAssets['shop-js'] = {"init-fed-cm":["modules/v2/client.init-fed-cm_CuFKFJvj.en.esm.js","modules/v2/chunk.common_07akgxkB.esm.js"],"init-windoid":["modules/v2/client.init-windoid_DzAujjXA.en.esm.js","modules/v2/chunk.common_07akgxkB.esm.js"],"shop-cash-offers":["modules/v2/client.shop-cash-offers_zk7-L2ai.en.esm.js","modules/v2/chunk.common_07akgxkB.esm.js","modules/v2/chunk.modal_BimtL6Xh.esm.js"],"shop-button":["modules/v2/client.shop-button_Ce6dXYkV.en.esm.js","modules/v2/chunk.common_07akgxkB.esm.js"],"shop-toast-manager":["modules/v2/client.shop-toast-manager_DtMj-pei.en.esm.js","modules/v2/chunk.common_07akgxkB.esm.js"],"avatar":["modules/v2/client.avatar_BTnouDA3.en.esm.js"],"pay-button":["modules/v2/client.pay-button_DMrB7rvr.en.esm.js","modules/v2/chunk.common_07akgxkB.esm.js"],"checkout-modal":["modules/v2/client.checkout-modal_DZVFXcVy.en.esm.js","modules/v2/chunk.common_07akgxkB.esm.js","modules/v2/chunk.modal_BimtL6Xh.esm.js"],"init-shop-email-lookup-coordinator":["modules/v2/client.init-shop-email-lookup-coordinator_BlZ0E3ZQ.en.esm.js","modules/v2/chunk.common_07akgxkB.esm.js"],"init-customer-accounts-sign-up":["modules/v2/client.init-customer-accounts-sign-up_CwzSvEf3.en.esm.js","modules/v2/client.shop-login-button_DPkfrFnj.en.esm.js","modules/v2/chunk.common_07akgxkB.esm.js","modules/v2/chunk.modal_BimtL6Xh.esm.js"],"init-customer-accounts":["modules/v2/client.init-customer-accounts_Cf3glfP8.en.esm.js","modules/v2/client.shop-login-button_DPkfrFnj.en.esm.js","modules/v2/chunk.common_07akgxkB.esm.js","modules/v2/chunk.modal_BimtL6Xh.esm.js"],"shop-login-button":["modules/v2/client.shop-login-button_DPkfrFnj.en.esm.js","modules/v2/chunk.common_07akgxkB.esm.js","modules/v2/chunk.modal_BimtL6Xh.esm.js"],"shop-follow-button":["modules/v2/client.shop-follow-button_BZP8esyV.en.esm.js","modules/v2/chunk.common_07akgxkB.esm.js","modules/v2/chunk.modal_BimtL6Xh.esm.js"],"init-shop-for-new-customer-accounts":["modules/v2/client.init-shop-for-new-customer-accounts_C4ubJDQY.en.esm.js","modules/v2/client.shop-login-button_DPkfrFnj.en.esm.js","modules/v2/chunk.common_07akgxkB.esm.js","modules/v2/chunk.modal_BimtL6Xh.esm.js"],"lead-capture":["modules/v2/client.lead-capture_dX-oGKJ-.en.esm.js","modules/v2/chunk.common_07akgxkB.esm.js","modules/v2/chunk.modal_BimtL6Xh.esm.js"],"payment-terms":["modules/v2/client.payment-terms_CeFYxelD.en.esm.js","modules/v2/chunk.common_07akgxkB.esm.js","modules/v2/chunk.modal_BimtL6Xh.esm.js"]};
</script>
<script id="__st">var __st={"a":17758583,"offset":-25200,"reqid":"4ea722c1-df2f-49fa-968d-3c47e788210c-1749716125","pageurl":"themes.materializecss.com\/pages\/admin-dashboard","s":"pages-18649776217","u":"17ff2d60358e","p":"page","rtyp":"page","rid":18649776217};</script>
<script>window.ShopifyPaypalV4VisibilityTracking = true;</script>
<script id="captcha-bootstrap">!function(){'use strict';const t='contact',e='account',n='new_comment',o=[[t,t],['blogs',n],['comments',n],[t,'customer']],c=[[e,'customer_login'],[e,'guest_login'],[e,'recover_customer_password'],[e,'create_customer']],r=t=>t.map((([t,e])=>`form[action*='/${t}']:not([data-nocaptcha='true']) input[name='form_type'][value='${e}']`)).join(','),a=t=>()=>t?[...document.querySelectorAll(t)].map((t=>t.form)):[];function s(){const t=[...o],e=r(t);return a(e)}const i='password',u='form_key',d=['recaptcha-v3-token','g-recaptcha-response','h-captcha-response',i],f=()=>{try{return window.sessionStorage}catch{return}},m='__shopify_v',_=t=>t.elements[u];function p(t,e,n=!1){try{const o=window.sessionStorage,c=JSON.parse(o.getItem(e)),{data:r}=function(t){const{data:e,action:n}=t;return t[m]||n?{data:e,action:n}:{data:t,action:n}}(c);for(const[e,n]of Object.entries(r))t.elements[e]&&(t.elements[e].value=n);n&&o.removeItem(e)}catch(o){console.error('form repopulation failed',{error:o})}}const l='form_type',E='cptcha';function T(t){t.dataset[E]=!0}const w=window,h=w.document,L='Shopify',v='ce_forms',y='captcha';let A=!1;((t,e)=>{const n=(g='f06e6c50-85a8-45c8-87d0-21a2b65856fe',I='https://cdn.shopify.com/shopifycloud/storefront-forms-hcaptcha/ce_storefront_forms_captcha_hcaptcha.v1.5.2.iife.js',D={infoText:'Protected by hCaptcha',privacyText:'Privacy',termsText:'Terms'},(t,e,n)=>{const o=w[L][v],c=o.bindForm;if(c)return c(t,g,e,D).then(n);var r;o.q.push([[t,g,e,D],n]),r=I,A||(h.body.append(Object.assign(h.createElement('script'),{id:'captcha-provider',async:!0,src:r})),A=!0)});var g,I,D;w[L]=w[L]||{},w[L][v]=w[L][v]||{},w[L][v].q=[],w[L][y]=w[L][y]||{},w[L][y].protect=function(t,e){n(t,void 0,e),T(t)},Object.freeze(w[L][y]),function(t,e,n,w,h,L){const[v,y,A,g]=function(t,e,n){const i=e?o:[],u=t?c:[],d=[...i,...u],f=r(d),m=r(i),_=r(d.filter((([t,e])=>n.includes(e))));return[a(f),a(m),a(_),s()]}(w,h,L),I=t=>{const e=t.target;return e instanceof HTMLFormElement?e:e&&e.form},D=t=>v().includes(t);t.addEventListener('submit',(t=>{const e=I(t);if(!e)return;const n=D(e)&&!e.dataset.hcaptchaBound&&!e.dataset.recaptchaBound,o=_(e),c=g().includes(e)&&(!o||!o.value);(n||c)&&t.preventDefault(),c&&!n&&(function(t){try{if(!f())return;!function(t){const e=f();if(!e)return;const n=_(t);if(!n)return;const o=n.value;o&&e.removeItem(o)}(t);const e=Array.from(Array(32),(()=>Math.random().toString(36)[2])).join('');!function(t,e){_(t)||t.append(Object.assign(document.createElement('input'),{type:'hidden',name:u})),t.elements[u].value=e}(t,e),function(t,e){const n=f();if(!n)return;const o=[...t.querySelectorAll(`input[type='${i}']`)].map((({name:t})=>t)),c=[...d,...o],r={};for(const[a,s]of new FormData(t).entries())c.includes(a)||(r[a]=s);n.setItem(e,JSON.stringify({[m]:1,action:t.action,data:r}))}(t,e)}catch(e){console.error('failed to persist form',e)}}(e),e.submit())}));const S=(t,e)=>{t&&!t.dataset[E]&&(n(t,e.some((e=>e===t))),T(t))};for(const o of['focusin','change'])t.addEventListener(o,(t=>{const e=I(t);D(e)&&S(e,y())}));const B=e.get('form_key'),M=e.get(l),P=B&&M;t.addEventListener('DOMContentLoaded',(()=>{const t=y();if(P)for(const e of t)e.elements[l].value===M&&p(e,B);[...new Set([...A(),...v().filter((t=>'true'===t.dataset.shopifyCaptcha))])].forEach((e=>S(e,t)))}))}(h,new URLSearchParams(w.location.search),n,t,e,['guest_login'])})(!0,!0)}();</script>
<script integrity="sha256-w1TMG8bx+vw+BuOfT7Dh2avfdjByyjlNYGyp9vJB5oo=" data-source-attribution="shopify.loadfeatures" defer="defer" src="//themes.materializecss.com/cdn/shopifycloud/shopify/assets/storefront/load_feature-c354cc1bc6f1fafc3e06e39f4fb0e1d9abdf763072ca394d606ca9f6f241e68a.js" crossorigin="anonymous"></script>
<script crossorigin="anonymous" defer="defer" src="//themes.materializecss.com/cdn/shopifycloud/shopify/assets/shopify_pay/storefront-80e528be853eac23af2454534897ca9536b1d3d04aa043b042f34879a3c111c8.js?v=20220906"></script>
<script data-source-attribution="shopify.dynamic_checkout.dynamic.init">var Shopify=Shopify||{};Shopify.PaymentButton=Shopify.PaymentButton||{isStorefrontPortableWallets:!0,init:function(){window.Shopify.PaymentButton.init=function(){};var t=document.createElement("script");t.src="https://themes.materializecss.com/cdn/shopifycloud/portable-wallets/latest/portable-wallets.en.js",t.type="module",document.head.appendChild(t)}};
</script>
<script data-source-attribution="shopify.dynamic_checkout.buyer_consent">
  function portableWalletsHideBuyerConsent(e){var t=document.getElementById("shopify-buyer-consent"),n=document.getElementById("shopify-subscription-policy-button");t&&n&&(t.classList.add("hidden"),t.setAttribute("aria-hidden","true"),n.removeEventListener("click",e))}function portableWalletsShowBuyerConsent(e){var t=document.getElementById("shopify-buyer-consent"),n=document.getElementById("shopify-subscription-policy-button");t&&n&&(t.classList.remove("hidden"),t.removeAttribute("aria-hidden"),n.addEventListener("click",e))}window.Shopify?.PaymentButton&&(window.Shopify.PaymentButton.hideBuyerConsent=portableWalletsHideBuyerConsent,window.Shopify.PaymentButton.showBuyerConsent=portableWalletsShowBuyerConsent);
</script>
<script data-source-attribution="shopify.dynamic_checkout.cart.bootstrap">document.addEventListener("DOMContentLoaded",(function(){function t(){return document.querySelector("shopify-accelerated-checkout-cart, shopify-accelerated-checkout")}if(t())Shopify.PaymentButton.init();else{new MutationObserver((function(e,n){t()&&(Shopify.PaymentButton.init(),n.disconnect())})).observe(document.body,{childList:!0,subtree:!0})}}));
</script>
<link href="css/bootstrap.min.css" rel="stylesheet">
<link rel="stylesheet" href="css/style.css">
<script src="js/bootstrap.bundle.min.js"></script>

<link rel="stylesheet" media="screen" href="https://themes.materializecss.com/cdn/shopifycloud/portable-wallets/latest/accelerated-checkout-backwards-compat.css" crossorigin="anonymous">

<style id="shopify-accelerated-checkout-cart">
        #shopify-buyer-consent {
  margin-top: 1em;
  display: inline-block;
  width: 100%;
}

#shopify-buyer-consent.hidden {
  display: none;
}

#shopify-subscription-policy-button {
  background: none;
  border: none;
  padding: 0;
  text-decoration: underline;
  font-size: inherit;
  cursor: pointer;
}

#shopify-subscription-policy-button::before {
  box-shadow: none;
}

      </style>
<script>window.performance && window.performance.mark && window.performance.mark('shopify.content_for_header.end');</script>
  <link rel="canonical" href="https://themes.materializecss.com/pages/admin-dashboard">
<link href="https://monorail-edge.shopifysvc.com" rel="dns-prefetch">
<script>(function(){if ("sendBeacon" in navigator && "performance" in window) {var session_token = document.cookie.match(/_shopify_s=([^;]*)/);function handle_abandonment_event(e) {var entries = performance.getEntries().filter(function(entry) {return /monorail-edge.shopifysvc.com/.test(entry.name);});if (!window.abandonment_tracked && entries.length === 0) {window.abandonment_tracked = true;var currentMs = Date.now();var navigation_start = performance.timing.navigationStart;var payload = {shop_id: 17758583,url: window.location.href,navigation_start,duration: currentMs - navigation_start,session_token: session_token && session_token.length === 2 ? session_token[1] : "",page_type: "page"};window.navigator.sendBeacon("https://monorail-edge.shopifysvc.com/v1/produce", JSON.stringify({schema_id: "online_store_buyer_site_abandonment/1.1",payload: payload,metadata: {event_created_at_ms: currentMs,event_sent_at_ms: currentMs}}));}}window.addEventListener('pagehide', handle_abandonment_event);}}());</script>
<script id="web-pixels-manager-setup">(function e(e,d,r,n,o,i){if(void 0===i&&(i={}),!Boolean(null===(t=null===(a=window.Shopify)||void 0===a?void 0:a.analytics)||void 0===t?void 0:t.replayQueue)){var a,t;window.Shopify=window.Shopify||{};var s=window.Shopify;s.analytics=s.analytics||{};var l=s.analytics;l.replayQueue=[],l.publish=function(e,d,r){return l.replayQueue.push([e,d,r]),!0};try{self.performance.mark("wpm:start")}catch(e){}var u=function(){var e={modern:/Edge?\/(1{2}[4-9]|1[2-9]\d|[2-9]\d{2}|\d{4,})\.\d+(\.\d+|)|Firefox\/(1{2}[4-9]|1[2-9]\d|[2-9]\d{2}|\d{4,})\.\d+(\.\d+|)|Chrom(ium|e)\/(9{2}|\d{3,})\.\d+(\.\d+|)|(Maci|X1{2}).+ Version\/(15\.\d+|(1[6-9]|[2-9]\d|\d{3,})\.\d+)([,.]\d+|)( \(\w+\)|)( Mobile\/\w+|) Safari\/|Chrome.+OPR\/(9{2}|\d{3,})\.\d+\.\d+|(CPU[ +]OS|iPhone[ +]OS|CPU[ +]iPhone|CPU IPhone OS|CPU iPad OS)[ +]+(15[._]\d+|(1[6-9]|[2-9]\d|\d{3,})[._]\d+)([._]\d+|)|Android:?[ /-](13[3-9]|1[4-9]\d|[2-9]\d{2}|\d{4,})(\.\d+|)(\.\d+|)|Android.+Firefox\/(13[5-9]|1[4-9]\d|[2-9]\d{2}|\d{4,})\.\d+(\.\d+|)|Android.+Chrom(ium|e)\/(13[3-9]|1[4-9]\d|[2-9]\d{2}|\d{4,})\.\d+(\.\d+|)|SamsungBrowser\/([2-9]\d|\d{3,})\.\d+/,legacy:/Edge?\/(1[6-9]|[2-9]\d|\d{3,})\.\d+(\.\d+|)|Firefox\/(5[4-9]|[6-9]\d|\d{3,})\.\d+(\.\d+|)|Chrom(ium|e)\/(5[1-9]|[6-9]\d|\d{3,})\.\d+(\.\d+|)([\d.]+$|.*Safari\/(?![\d.]+ Edge\/[\d.]+$))|(Maci|X1{2}).+ Version\/(10\.\d+|(1[1-9]|[2-9]\d|\d{3,})\.\d+)([,.]\d+|)( \(\w+\)|)( Mobile\/\w+|) Safari\/|Chrome.+OPR\/(3[89]|[4-9]\d|\d{3,})\.\d+\.\d+|(CPU[ +]OS|iPhone[ +]OS|CPU[ +]iPhone|CPU IPhone OS|CPU iPad OS)[ +]+(10[._]\d+|(1[1-9]|[2-9]\d|\d{3,})[._]\d+)([._]\d+|)|Android:?[ /-](13[3-9]|1[4-9]\d|[2-9]\d{2}|\d{4,})(\.\d+|)(\.\d+|)|Mobile Safari.+OPR\/([89]\d|\d{3,})\.\d+\.\d+|Android.+Firefox\/(13[5-9]|1[4-9]\d|[2-9]\d{2}|\d{4,})\.\d+(\.\d+|)|Android.+Chrom(ium|e)\/(13[3-9]|1[4-9]\d|[2-9]\d{2}|\d{4,})\.\d+(\.\d+|)|Android.+(UC? ?Browser|UCWEB|U3)[ /]?(15\.([5-9]|\d{2,})|(1[6-9]|[2-9]\d|\d{3,})\.\d+)\.\d+|SamsungBrowser\/(5\.\d+|([6-9]|\d{2,})\.\d+)|Android.+MQ{2}Browser\/(14(\.(9|\d{2,})|)|(1[5-9]|[2-9]\d|\d{3,})(\.\d+|))(\.\d+|)|K[Aa][Ii]OS\/(3\.\d+|([4-9]|\d{2,})\.\d+)(\.\d+|)/},d=e.modern,r=e.legacy,n=navigator.userAgent;return n.match(d)?"modern":n.match(r)?"legacy":"unknown"}(),c="modern"===u?"modern":"legacy",f=(null!=o?o:{modern:"",legacy:""})[c],m=function(e){return[e.baseUrl,"/wpm","/b",e.hashVersion,"modern"===e.buildTarget?"m":"l",".js"].join("")}({baseUrl:r,hashVersion:n,buildTarget:c}),p=function(e){var d=e.version,r=e.bundleTarget,n=e.surface,o=e.pageUrl,i=e.monorailEndpoint;return{emit:function(e){var a=e.status,t=e.errorMsg,s=(new Date).getTime(),l=JSON.stringify({metadata:{event_sent_at_ms:s},events:[{schema_id:"web_pixels_manager_load/3.1",payload:{version:d,bundle_target:r,page_url:o,status:a,surface:n,error_msg:t},metadata:{event_created_at_ms:s}}]});if(!i)return console&&console.warn&&console.warn("[Web Pixels Manager] No Monorail endpoint provided, skipping logging."),!1;try{return self.navigator.sendBeacon.bind(self.navigator)(i,l)}catch(e){}var u=new XMLHttpRequest;try{return u.open("POST",i,!0),u.setRequestHeader("Content-Type","text/plain"),u.send(l),!0}catch(e){return console&&console.warn&&console.warn("[Web Pixels Manager] Got an unhandled error while logging to Monorail."),!1}}}}({version:n,bundleTarget:u,surface:e.surface,pageUrl:self.location.href,monorailEndpoint:e.monorailEndpoint});try{i.browserTarget=u,function(e){var d=e.src,r=e.async,n=void 0===r||r,o=e.onload,i=e.onerror,a=e.sri,t=e.scriptDataAttributes,s=void 0===t?{}:t,l=document.createElement("script"),u=document.querySelector("head"),c=document.querySelector("body");if(l.async=n,l.src=d,a&&(l.integrity=a,l.crossOrigin="anonymous"),s)for(var f in s)if(Object.prototype.hasOwnProperty.call(s,f))try{l.dataset[f]=s[f]}catch(e){}if(o&&l.addEventListener("load",o),i&&l.addEventListener("error",i),u)u.appendChild(l);else{if(!c)throw new Error("Did not find a head or body element to append the script");c.appendChild(l)}}({src:m,async:!0,onload:function(){if(!function(){var e,d;return Boolean(null===(d=null===(e=window.Shopify)||void 0===e?void 0:e.analytics)||void 0===d?void 0:d.initialized)}()){var r=window.webPixelsManager.init(e)||void 0;if(r){d(r);var n=window.Shopify.analytics;n.replayQueue.forEach((function(e){var d=e[0],n=e[1],o=e[2];r.publishCustomEvent(d,n,o)})),n.replayQueue=[],n.publish=r.publishCustomEvent,n.visitor=r.visitor,n.initialized=!0}}},onerror:function(){return p.emit({status:"failed",errorMsg:"".concat(m," has failed to load")})},sri:function(e){var d=/^sha384-[A-Za-z0-9+/=]+$/;return"string"==typeof e&&d.test(e)}(f)?f:"",scriptDataAttributes:i}),p.emit({status:"loading"})}catch(e){p.emit({status:"failed",errorMsg:(null==e?void 0:e.message)||"Unknown error"})}}})({shopId: 17758583,storefrontBaseUrl: "https://themes.materializecss.com",extensionsBaseUrl: "https://extensions.shopifycdn.com/cdn/shopifycloud/web-pixels-manager",monorailEndpoint: "https://monorail-edge.shopifysvc.com/unstable/produce_batch",surface: "storefront-renderer",enabledBetaFlags: ["ac843a20"],webPixelsConfigList: [{"id":"86212697","eventPayloadVersion":"v1","runtimeContext":"LAX","scriptVersion":"1","type":"CUSTOM","privacyPurposes":["ANALYTICS"],"name":"Google Analytics tag (migrated)"},{"id":"shopify-app-pixel","configuration":"{}","eventPayloadVersion":"v1","runtimeContext":"STRICT","scriptVersion":"0420","apiClientId":"shopify-pixel","type":"APP","privacyPurposes":["ANALYTICS","MARKETING"]},{"id":"shopify-custom-pixel","eventPayloadVersion":"v1","runtimeContext":"LAX","scriptVersion":"0420","apiClientId":"shopify-pixel","type":"CUSTOM","privacyPurposes":["ANALYTICS","MARKETING"]}],isMerchantRequest: false,effectiveTopLevelDomain: "com",initData: {"shop":{"name":"Materialize Themes","paymentSettings":{"currencyCode":"USD"},"myshopifyDomain":"materialize-themes.myshopify.com","countryCode":"US","storefrontUrl":"https://themes.materializecss.com"},"customer":null,"cart":null,"checkout":null,"productVariants":[],"purchasingCompany":null},},function pageEvents(webPixelsManagerAPI) {webPixelsManagerAPI.publish("page_viewed", {});},"https://themes.materializecss.com/cdn","9bbdfe5aw65c3e475p7ee20287m7e1dae13",{"modern":"","legacy":""},{"shopId":"17758583","storefrontBaseUrl":"https://themes.materializecss.com","extensionBaseUrl":"https://extensions.shopifycdn.com/cdn/shopifycloud/web-pixels-manager","surface":"storefront-renderer","enabledBetaFlags":"[\"ac843a20\"]","isMerchantRequest":"false","hashVersion":"9bbdfe5aw65c3e475p7ee20287m7e1dae13"});</script><script>
  window.ShopifyAnalytics = window.ShopifyAnalytics || {};
  window.ShopifyAnalytics.meta = window.ShopifyAnalytics.meta || {};
  window.ShopifyAnalytics.meta.currency = 'JPY';
  var meta = {"page":{"pageType":"page","resourceType":"page","resourceId":18649776217}};
  for (var attr in meta) {
    window.ShopifyAnalytics.meta[attr] = meta[attr];
  }
</script>
<script class="analytics">
  (function () {
    var customDocumentWrite = function(content) {
      var jquery = null;

      if (window.jQuery) {
        jquery = window.jQuery;
      } else if (window.Checkout && window.Checkout.$) {
        jquery = window.Checkout.$;
      }

      if (jquery) {
        jquery('body').append(content);
      }
    };

    var hasLoggedConversion = function(token) {
      if (token) {
        return document.cookie.indexOf('loggedConversion=' + token) !== -1;
      }
      return false;
    }

    var setCookieIfConversion = function(token) {
      if (token) {
        var twoMonthsFromNow = new Date(Date.now());
        twoMonthsFromNow.setMonth(twoMonthsFromNow.getMonth() + 2);

        document.cookie = 'loggedConversion=' + token + '; expires=' + twoMonthsFromNow;
      }
    }

    var trekkie = window.ShopifyAnalytics.lib = window.trekkie = window.trekkie || [];
    if (trekkie.integrations) {
      return;
    }
    trekkie.methods = [
      'identify',
      'page',
      'ready',
      'track',
      'trackForm',
      'trackLink'
    ];
    trekkie.factory = function(method) {
      return function() {
        var args = Array.prototype.slice.call(arguments);
        args.unshift(method);
        trekkie.push(args);
        return trekkie;
      };
    };
    for (var i = 0; i < trekkie.methods.length; i++) {
      var key = trekkie.methods[i];
      trekkie[key] = trekkie.factory(key);
    }
    trekkie.load = function(config) {
      trekkie.config = config || {};
      trekkie.config.initialDocumentCookie = document.cookie;
      var first = document.getElementsByTagName('script')[0];
      var script = document.createElement('script');
      script.type = 'text/javascript';
      script.onerror = function(e) {
        var scriptFallback = document.createElement('script');
        scriptFallback.type = 'text/javascript';
        scriptFallback.onerror = function(error) {
                var Monorail = {
      produce: function produce(monorailDomain, schemaId, payload) {
        var currentMs = new Date().getTime();
        var event = {
          schema_id: schemaId,
          payload: payload,
          metadata: {
            event_created_at_ms: currentMs,
            event_sent_at_ms: currentMs
          }
        };
        return Monorail.sendRequest("https://" + monorailDomain + "/v1/produce", JSON.stringify(event));
      },
      sendRequest: function sendRequest(endpointUrl, payload) {
        // Try the sendBeacon API
        if (window && window.navigator && typeof window.navigator.sendBeacon === 'function' && typeof window.Blob === 'function' && !Monorail.isIos12()) {
          var blobData = new window.Blob([payload], {
            type: 'text/plain'
          });

          if (window.navigator.sendBeacon(endpointUrl, blobData)) {
            return true;
          } // sendBeacon was not successful

        } // XHR beacon

        var xhr = new XMLHttpRequest();

        try {
          xhr.open('POST', endpointUrl);
          xhr.setRequestHeader('Content-Type', 'text/plain');
          xhr.send(payload);
        } catch (e) {
          console.log(e);
        }

        return false;
      },
      isIos12: function isIos12() {
        return window.navigator.userAgent.lastIndexOf('iPhone; CPU iPhone OS 12_') !== -1 || window.navigator.userAgent.lastIndexOf('iPad; CPU OS 12_') !== -1;
      }
    };
    Monorail.produce('monorail-edge.shopifysvc.com',
      'trekkie_storefront_load_errors/1.1',
      {shop_id: 17758583,
      theme_id: 133945025,
      app_name: "storefront",
      context_url: window.location.href,
      source_url: "//themes.materializecss.com/cdn/s/trekkie.storefront.9a0efb0bbe09f2f35ce5c61e4c28c122919587f7.min.js"});

        };
        scriptFallback.async = true;
        scriptFallback.src = '//themes.materializecss.com/cdn/s/trekkie.storefront.9a0efb0bbe09f2f35ce5c61e4c28c122919587f7.min.js';
        first.parentNode.insertBefore(scriptFallback, first);
      };
      script.async = true;
      script.src = '//themes.materializecss.com/cdn/s/trekkie.storefront.9a0efb0bbe09f2f35ce5c61e4c28c122919587f7.min.js';
      first.parentNode.insertBefore(script, first);
    };
    trekkie.load(
      {"Trekkie":{"appName":"storefront","development":false,"defaultAttributes":{"shopId":17758583,"isMerchantRequest":null,"themeId":133945025,"themeCityHash":"1670185919760860438","contentLanguage":"en","currency":"JPY","eventMetadataId":"45fedf0b-27da-4e68-8478-f9e6c306887f"},"isServerSideCookieWritingEnabled":true,"monorailRegion":"shop_domain"},"Session Attribution":{},"S2S":{"facebookCapiEnabled":false,"source":"trekkie-storefront-renderer","apiClientId":580111}}
    );

    var loaded = false;
    trekkie.ready(function() {
      if (loaded) return;
      loaded = true;

      window.ShopifyAnalytics.lib = window.trekkie;

      var originalDocumentWrite = document.write;
      document.write = customDocumentWrite;
      try { window.ShopifyAnalytics.merchantGoogleAnalytics.call(this); } catch(error) {};
      document.write = originalDocumentWrite;

      window.ShopifyAnalytics.lib.page(null,{"pageType":"page","resourceType":"page","resourceId":18649776217,"shopifyEmitted":true});

      var match = window.location.pathname.match(/checkouts\/(.+)\/(thank_you|post_purchase)/)
      var token = match? match[1]: undefined;
      if (!hasLoggedConversion(token)) {
        setCookieIfConversion(token);
        
      }
    });


        var eventsListenerScript = document.createElement('script');
        eventsListenerScript.async = true;
        eventsListenerScript.src = "//themes.materializecss.com/cdn/shopifycloud/shopify/assets/shop_events_listener-f55dd2979ec32029c7d9e0b454ab8b33f79c01ca039d17a6f5c9b95647564b19.js";
        document.getElementsByTagName('head')[0].appendChild(eventsListenerScript);

})();</script>
  <script>
  if (!window.ga || (window.ga && typeof window.ga !== 'function')) {
    window.ga = function ga() {
      (window.ga.q = window.ga.q || []).push(arguments);
      if (window.Shopify && window.Shopify.analytics && typeof window.Shopify.analytics.publish === 'function') {
        window.Shopify.analytics.publish("ga_stub_called", {}, {sendTo: "google_osp_migration"});
      }
      console.error("Shopify's Google Analytics stub called with:", Array.from(arguments), "\nSee https://help.shopify.com/manual/promoting-marketing/pixels/pixel-migration#google for more information.");
    };
    if (window.Shopify && window.Shopify.analytics && typeof window.Shopify.analytics.publish === 'function') {
      window.Shopify.analytics.publish("ga_stub_initialized", {}, {sendTo: "google_osp_migration"});
    }
  }
</script>

<script
  defer
  src="https://themes.materializecss.com/cdn/shopifycloud/perf-kit/shopify-perf-kit-1.6.5.min.js"
  data-application="storefront-renderer"
  data-shop-id="17758583"
  data-render-region="gcp-asia-southeast1"
  data-page-type="page"
  data-theme-instance-id="133945025"
  data-theme-name="Debut"
  data-theme-version="1.0.1"
  data-monorail-region="shop_domain"
  data-resource-timing-sampling-rate="10"
  data-shs="true"
></script>
</head>

<link href="css/bootstrap.min.css" rel="stylesheet">
<link rel="stylesheet" href="css/style.css">
<script src="js/bootstrap.bundle.min.js"></script>
	<%
	String uri = request.getRequestURI();
	%>
	<header>
		<nav class="navbar">
			<div class="logo">物品売上管理システム</div>
			<ul class="nav-links">
				<li><a class="<%=uri.endsWith("C0020.jsp") ? "active" : ""%>"
					href="C0020.html">ダッシュボード</a></li>
				<li><a class="<%=uri.endsWith("S0010.jsp") ? "active" : ""%>"
					href="S0010.html">売上登録</a></li>
				<li><a class="<%=uri.endsWith("S0020.jsp") ? "active" : ""%>"
					href="S0020.html">売上検索</a></li>
				<li><a class="<%=uri.endsWith("S0030.jsp") ? "active" : ""%>"
					href="S0030.html">アカウント登録</a></li>
				<li><a class="<%=uri.endsWith("C0040.jsp") ? "active" : ""%>"
					href="C0040.html">アカウント検索</a></li>
				<li class="logout"><a href="logout.jsp">ログアウト</a></li>
			</ul>
		</nav>
	</header>
  <body>

    <main><div class="container">
  <div class="masonry row">
    <div class="col s12">
      <h2>Dashboard</h2>
    </div>
    <div class="col l3 m6 s12">

      <div class="card">
        <div class="card-stacked">
          <div class="card-metrics card-metrics-static">
            <div class="card-metric">
              <div class="card-metric-title">総売上</div>
              <div class="card-metric-value">$12,476.00</div>
              <div class="card-metric-change increase">
                <i class="material-icons left">keyboard_arrow_up</i>
                12%
              </div>
            </div>
          </div>
        </div>
        <div class="card-chart">
          <canvas id="flush-area-chart-blue" height="100px"></canvas>
        </div>
      </div>

    </div>
    <div class="col l3 m6 s12">

      <div class="card">
        <div class="card-stacked">
          <div class="card-metrics card-metrics-static">
            <div class="card-metric">
              <div class="card-metric-title">アクセス数</div>
              <div class="card-metric-value">11,893</div>
              <div class="card-metric-change increase">
                <i class="material-icons left">keyboard_arrow_up</i>
                8%
              </div>
            </div>
          </div>
        </div>
        <div class="card-chart">
          <canvas id="flush-area-chart-yellow" height="100px"></canvas>
        </div>
      </div>

    </div>
    <div class="col l3 m6 s12">

      <div class="card">
        <div class="card-stacked">
          <div class="card-metrics card-metrics-static">
            <div class="card-metric">
              <div class="card-metric-title">登録者数</div>
              <div class="card-metric-value">230,648</div>
              <div class="card-metric-change decrease">
                <i class="material-icons left">keyboard_arrow_down</i>
                2%
              </div>
            </div>
          </div>
        </div>
        <div class="card-chart">
          <canvas id="flush-area-chart-pink" height="100"></canvas>
        </div>
      </div>

    </div>
    <div class="col l3 m6 s12">

      <div class="card">
        <div class="card-stacked">
          <div class="card-metrics card-metrics-static">
            <div class="card-metric">
              <div class="card-metric-title">Conversion Rate</div>
              <div class="card-metric-value">0.24%</div>
              <div class="card-metric-change decrease">
                <i class="material-icons left">keyboard_arrow_down</i>
                9%
              </div>
            </div>
          </div>
        </div>
        <div class="card-chart">
          <canvas id="flush-area-chart-green" height="100"></canvas>
        </div>
      </div>

    </div>

    <div class="col s12">

      <div class="card">
        <div class="card-metrics card-metrics-toggle card-metrics-centered">
          <div class="card-metric waves-effect active" data-metric="revenue">
            <div class="card-metric-title">Revenue</div>
            <div class="card-metric-value">$12,476.00</div>
            <div class="card-metric-change">
              <i class="material-icons">keyboard_arrow_up</i>
              12%
            </div>
          </div>
          <div class="card-metric waves-effect" data-metric="users">
            <div class="card-metric-title">Users</div>
            <div class="card-metric-value">2024</div>
            <div class="card-metric-change">
              <i class="material-icons">keyboard_arrow_up</i>
              9%
            </div>
          </div>
          <div class="card-metric waves-effect" data-metric="ctr">
            <div class="card-metric-title">CTR</div>
            <div class="card-metric-value">0.20%</div>
            <div class="card-metric-change">
              <i class="material-icons">keyboard_arrow_up</i>
              4%
            </div>
          </div>
        </div>
        <div class="card-content">
          <canvas class="card-chart" id="main-toggle-line-chart" width="400" height="400"></canvas>
        </div>
      </div>

    </div>

    <div class="col m6 s12">
      <div class="card">
        <div class="card-content">
          <span class="card-title">Updates</span>
          <ul class="badge-updates">
            <li>
              <span class="new badge red" data-badge-caption="refund"></span>
              <span class="message">45$ refunded to Alan Chang</span>
              <span class="time">14 minutes ago</span>
            </li>
            <li>
              <span class="new badge green" data-badge-caption="purchase"></span>
              <span class="message">45$ from Alan Chang</span>
              <span class="time">14 minutes ago</span>
            </li>
            <li>
              <span class="new badge red" data-badge-caption="refund"></span>
              <span class="message">45$ refunded to Alan Chang</span>
              <span class="time">14 minutes ago</span>
            </li>
          </ul>
        </div>
      </div>
    </div>

    <div class="col m6 s12">
      <div id="tab-legend-chart-card" class="card primary-color">
        <div class="card-content">
          <p class="white-text">I am a very simple card. I am good at containing small bits of information. I am convenient because I require little markup to use effectively.</p>
        </div>
        <div class="card-content">
          <canvas class="card-chart" id="tab-legend-line-chart" width="400" height="400"></canvas>
        </div>
      </div>
    </div>

    <div class="col m6 s12">
      <div class="card">
        <div class="card-content">
          <div class="card-title">Stacked Bar Chart</div>
          <div class="chart-wrapper">
            <canvas id="stacked-bar-chart" width="400" height="400"></canvas>
          </div>
        </div>
      </div>
    </div>



    <div class="col m6 s12">
      <div class="card">
        <div class="card-content">
          <span class="card-title">Devices</span>
          <div class="row row-vertical-center">
            <div class="col s6">
              <canvas id="doughnut-chart" width="50%"></canvas>
            </div>
            <div class="col s6">
              <div class="chart-legend-wrapper"></div>
            </div>
          </div>
        </div>
      </div>
    </div>
  </div>

  <div class="masonry row">
    <div class="col s12">
      <h2>Secondary Data</h2>
    </div>

    <div class="col m6 s12">
      <div class="card">
        <div class="card-content">
          <div id="vmap" style="width: 100%; height: 400px;"></div>
        </div>
      </div>
    </div>

    <div class="col m6 s12">
      <div class="card">
        <div id='calendar'></div>
      </div>
    </div>

    <div class="col s12">
      <div class="card">
        <table id="default-table-example" class="row-border" cellspacing="0" width="100%">
          <thead>
            <tr>
              <th><label><input name="select_all" value="1" id="example-select-all" type="checkbox" /><span></span></label></th>
              <th>Name</th>
              <th>Position</th>
              <th>Office</th>
              <th>Age</th>
              <th>Start date</th>
              <th>Salary</th>
            </tr>
          </thead>
         
          <tbody>
            <tr>
              <td>Tiger Nixon</td>
              <td>System Architect</td>
              <td>Edinburgh</td>
              <td>61</td>
              <td>2011/04/25</td>
              <td>$320,800</td>
            </tr>
            <tr>
              <td>Garrett Winters</td>
              <td>Accountant</td>
              <td>Tokyo</td>
              <td>63</td>
              <td>2011/07/25</td>
              <td>$170,750</td>
            </tr>
            <tr>
              <td>Ashton Cox</td>
              <td>Junior Technical Author</td>
              <td>San Francisco</td>
              <td>66</td>
              <td>2009/01/12</td>
              <td>$86,000</td>
            </tr>
            <tr>
              <td>Cedric Kelly</td>
              <td>Senior Javascript Developer</td>
              <td>Edinburgh</td>
              <td>22</td>
              <td>2012/03/29</td>
              <td>$433,060</td>
            </tr>
            <tr>
              <td>Airi Satou</td>
              <td>Accountant</td>
              <td>Tokyo</td>
              <td>33</td>
              <td>2008/11/28</td>
              <td>$162,700</td>
            </tr>
          </tbody>
        </table>
      </div>

    </div>
  </div>
</div>
    </main><footer class="page-footer">
  <div class="container">
    <div class="row">
      <div class="col s6 m3">
        <img class="materialize-logo" src="//themes.materializecss.com/cdn/shop/t/1/assets/materialize-logo.png?v=179096390591197713681528499937" alt="Materialize">
        <p>Made with love by Materialize.</p>
      </div>
      <div class="col s6 m3">
        <h5>About</h5>
        <ul>
          <li><a href="#!">Blog</a></li>
          <li><a href="#!">Pricing</a></li>
          <li><a href="#!">Docs</a></li>
        </ul>
      </div>
      <div class="col s6 m3">
        <h5>Connect</h5>
        <ul>
          <li><a href="#!">Community</a></li>
          <li><a href="#!">Subscribe</a></li>
          <li><a href="#!">Email</a></li>
        </ul>
      </div>
      <div class="col s6 m3">
        <h5>Contact</h5>
        <ul>
          <li><a href="#!">Twitter</a></li>
          <li><a href="#!">Facebook</a></li>
          <li><a href="#!">Github</a></li>
        </ul>
      </div>
    </div>
  </div>
</footer><!-- Scripts -->
<script src="https://ajax.googleapis.com/ajax/libs/jquery/2.1.1/jquery.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/moment.js/2.19.2/moment.min.js"></script>

<!-- External libraries -->

<!-- jqvmap -->
<script type="text/javascript" src="//themes.materializecss.com/cdn/shop/t/1/assets/jquery.vmap.min.js?v=163123838929084484541528498367"></script>
<script type="text/javascript" src="//themes.materializecss.com/cdn/shop/t/1/assets/jquery.vmap.world.js?v=72513650592694871481528498398" charset="utf-8"></script>
<script type="text/javascript" src="//themes.materializecss.com/cdn/shop/t/1/assets/jquery.vmap.sampledata.js?v=87023629870755566851528498384"></script>

<!-- ChartJS -->
<script type="text/javascript" src="//themes.materializecss.com/cdn/shop/t/1/assets/Chart.js?v=28848919051585277061528498087"></script>
<script type="text/javascript" src="//themes.materializecss.com/cdn/shop/t/1/assets/Chart.Financial.js?v=34644991646752552951528498109"></script>


<script src="https://cdnjs.cloudflare.com/ajax/libs/fullcalendar/3.7.0/fullcalendar.min.js"></script>
<script type="text/javascript" src="https://cdn.datatables.net/v/dt/dt-1.10.16/datatables.min.js"></script>
<script src="//themes.materializecss.com/cdn/shop/t/1/assets/imagesloaded.pkgd.min.js?v=58819771796763510541528498326"></script>
<script src="//themes.materializecss.com/cdn/shop/t/1/assets/masonry.pkgd.min.js?v=180312904682597569011528498229"></script>


<!-- Initialization script -->
<script src="//themes.materializecss.com/cdn/shop/t/1/assets/dashboard.min.js?v=4816808830627109061528498722"></script>
  </body>
</html>