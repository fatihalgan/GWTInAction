function org_gwtbook_Dashboard(){var P='bootstrap',Q='begin',R='gwt.codesvr.org.gwtbook.Dashboard=',S='gwt.codesvr=',T='org.gwtbook.Dashboard',U='startup',V='DUMMY',W=0,X=1,Y='iframe',Z='javascript:""',$='position:absolute; width:0; height:0; border:none; left: -1000px;',_=' top: -1000px;',ab='CSS1Compat',bb='<!doctype html>',cb='',db='<html><head><\/head><body><\/body><\/html>',eb='undefined',fb='DOMContentLoaded',gb=50,hb='Chrome',ib='eval("',jb='");',kb='script',lb='javascript',mb='moduleStartup',nb='moduleRequested',ob='org_gwtbook_Dashboard',pb='Failed to load ',qb='head',rb='meta',sb='name',tb='org.gwtbook.Dashboard::',ub='::',vb='gwt:property',wb='content',xb='=',yb='gwt:onPropertyErrorFn',zb='Bad handler "',Ab='" for "gwt:onPropertyErrorFn"',Bb='gwt:onLoadErrorFn',Cb='" for "gwt:onLoadErrorFn"',Db='#',Eb='?',Fb='/',Gb='img',Hb='clear.cache.gif',Ib='baseUrl',Jb='org.gwtbook.Dashboard.nocache.js',Kb='base',Lb='//',Mb='externalvisibility',Nb='internet',Ob='locale',Pb='default',Qb='locale=',Rb=7,Sb='&',Tb='__gwt_Locale',Ub='_',Vb='Unexpected exception in locale detection, using default: ',Wb=2,Xb='user.agent',Yb='webkit',Zb='safari',$b='msie',_b=10,ac=11,bc='ie10',cc=9,dc='ie9',ec=8,fc='ie8',gc='gecko',hc='gecko1_8',ic=3,jc=4,kc='selectingPermutation',lc='org.gwtbook.Dashboard.devmode.js',mc="GWT module 'org.gwtbook.Dashboard' may need to be (re)compiled",nc=':',oc='.cache.js',pc='link',qc='rel',rc='stylesheet',sc='href',tc='loadExternalRefs',uc='CSS/Calculator.css',vc='end',wc='http:',xc='file:',yc='_gwt_dummy_',zc='__gwtDevModeHook:org.gwtbook.Dashboard',Ac='Ignoring non-whitelisted Dev Mode URL: ',Bc=':moduleBase';var p=window;var q=document;s(P,Q);function r(){var a=p.location.search;return a.indexOf(R)!=-1||a.indexOf(S)!=-1}
function s(a,b){if(p.__gwtStatsEvent){p.__gwtStatsEvent({moduleName:T,sessionId:p.__gwtStatsSessionId,subSystem:U,evtGroup:a,millis:(new Date).getTime(),type:b})}}
org_gwtbook_Dashboard.__sendStats=s;org_gwtbook_Dashboard.__moduleName=T;org_gwtbook_Dashboard.__errFn=null;org_gwtbook_Dashboard.__moduleBase=V;org_gwtbook_Dashboard.__softPermutationId=W;org_gwtbook_Dashboard.__computePropValue=null;org_gwtbook_Dashboard.__getPropMap=null;org_gwtbook_Dashboard.__installRunAsyncCode=function(){};org_gwtbook_Dashboard.__gwtStartLoadingFragment=function(){return null};org_gwtbook_Dashboard.__gwt_isKnownPropertyValue=function(){return false};org_gwtbook_Dashboard.__gwt_getMetaProperty=function(){return null};var t=null;var u=p.__gwt_activeModules=p.__gwt_activeModules||{};u[T]={moduleName:T};org_gwtbook_Dashboard.__moduleStartupDone=function(e){var f=u[T].bindings;u[T].bindings=function(){var a=f?f():{};var b=e[org_gwtbook_Dashboard.__softPermutationId];for(var c=W;c<b.length;c++){var d=b[c];a[d[W]]=d[X]}return a}};var v;function w(){A();return v}
function A(){if(v){return}var a=q.createElement(Y);a.src=Z;a.id=T;a.style.cssText=$+_;a.tabIndex=-1;q.body.appendChild(a);v=a.contentDocument;if(!v){v=a.contentWindow.document}v.open();var b=document.compatMode==ab?bb:cb;v.write(b+db);v.close()}
function B(k){function l(a){function b(){if(typeof q.readyState==eb){return typeof q.body!=eb&&q.body!=null}return /loaded|complete/.test(q.readyState)}
var c=b();if(c){a();return}function d(){if(!c){c=true;a();if(q.removeEventListener){q.removeEventListener(fb,d,false)}if(e){clearInterval(e)}}}
if(q.addEventListener){q.addEventListener(fb,d,false)}var e=setInterval(function(){if(b()){d()}},gb)}
function m(c){function d(a,b){a.removeChild(b)}
var e=w();var f=e.body;var g;if(navigator.userAgent.indexOf(hb)>-1&&window.JSON){var h=e.createDocumentFragment();h.appendChild(e.createTextNode(ib));for(var i=W;i<c.length;i++){var j=window.JSON.stringify(c[i]);h.appendChild(e.createTextNode(j.substring(X,j.length-X)))}h.appendChild(e.createTextNode(jb));g=e.createElement(kb);g.language=lb;g.appendChild(h);f.appendChild(g);d(f,g)}else{for(var i=W;i<c.length;i++){g=e.createElement(kb);g.language=lb;g.text=c[i];f.appendChild(g);d(f,g)}}}
org_gwtbook_Dashboard.onScriptDownloaded=function(a){l(function(){m(a)})};s(mb,nb);var n=q.createElement(kb);n.src=k;if(org_gwtbook_Dashboard.__errFn){n.onerror=function(){org_gwtbook_Dashboard.__errFn(ob,new Error(pb+code))}}q.getElementsByTagName(qb)[W].appendChild(n)}
org_gwtbook_Dashboard.__startLoadingFragment=function(a){return F(a)};org_gwtbook_Dashboard.__installRunAsyncCode=function(a){var b=w();var c=b.body;var d=b.createElement(kb);d.language=lb;d.text=a;c.appendChild(d);c.removeChild(d)};function C(){var c={};var d;var e;var f=q.getElementsByTagName(rb);for(var g=W,h=f.length;g<h;++g){var i=f[g],j=i.getAttribute(sb),k;if(j){j=j.replace(tb,cb);if(j.indexOf(ub)>=W){continue}if(j==vb){k=i.getAttribute(wb);if(k){var l,m=k.indexOf(xb);if(m>=W){j=k.substring(W,m);l=k.substring(m+X)}else{j=k;l=cb}c[j]=l}}else if(j==yb){k=i.getAttribute(wb);if(k){try{d=eval(k)}catch(a){alert(zb+k+Ab)}}}else if(j==Bb){k=i.getAttribute(wb);if(k){try{e=eval(k)}catch(a){alert(zb+k+Cb)}}}}}__gwt_getMetaProperty=function(a){var b=c[a];return b==null?null:b};t=d;org_gwtbook_Dashboard.__errFn=e}
function D(){function e(a){var b=a.lastIndexOf(Db);if(b==-1){b=a.length}var c=a.indexOf(Eb);if(c==-1){c=a.length}var d=a.lastIndexOf(Fb,Math.min(c,b));return d>=W?a.substring(W,d+X):cb}
function f(a){if(a.match(/^\w+:\/\//)){}else{var b=q.createElement(Gb);b.src=a+Hb;a=e(b.src)}return a}
function g(){var a=__gwt_getMetaProperty(Ib);if(a!=null){return a}return cb}
function h(){var a=q.getElementsByTagName(kb);for(var b=W;b<a.length;++b){if(a[b].src.indexOf(Jb)!=-1){return e(a[b].src)}}return cb}
function i(){var a=q.getElementsByTagName(Kb);if(a.length>W){return a[a.length-X].href}return cb}
function j(){var a=q.location;return a.href==a.protocol+Lb+a.host+a.pathname+a.search+a.hash}
var k=g();if(k==cb){k=h()}if(k==cb){k=i()}if(k==cb&&j()){k=e(q.location.href)}k=f(k);return k}
function F(a){if(a.match(/^\//)){return a}if(a.match(/^[a-zA-Z]+:\/\//)){return a}return org_gwtbook_Dashboard.__moduleBase+a}
function G(){var i=[];var j=W;var k=[];var l=[];function m(a){var b=l[a](),c=k[a];if(b in c){return b}var d=[];for(var e in c){d[c[e]]=e}if(t){t(a,d,b)}throw null}
l[Mb]=function(){try{var b=__gwt_getMetaProperty(Mb);if(b==null){b=Nb}return b}catch(a){return Nb}};k[Mb]={internet:W,intranet:X};l[Ob]=function(){var b=null;var c=Pb;try{if(!b){var d=location.search;var e=d.indexOf(Qb);if(e>=W){var f=d.substring(e+Rb);var g=d.indexOf(Sb,e);if(g<W){g=d.length}b=d.substring(e+Rb,g)}}if(!b){b=__gwt_getMetaProperty(Ob)}if(!b){b=p[Tb]}if(b){c=b}while(b&&!__gwt_isKnownPropertyValue(Ob,b)){var h=b.lastIndexOf(Ub);if(h<W){b=null;break}b=b.substring(W,h)}}catch(a){alert(Vb+a)}p[Tb]=c;return b||Pb};k[Ob]={'default':W,en_US:X,tr:Wb};l[Xb]=function(){var a=navigator.userAgent.toLowerCase();var b=q.documentMode;if(function(){return a.indexOf(Yb)!=-1}())return Zb;if(function(){return a.indexOf($b)!=-1&&(b>=_b&&b<ac)}())return bc;if(function(){return a.indexOf($b)!=-1&&(b>=cc&&b<ac)}())return dc;if(function(){return a.indexOf($b)!=-1&&(b>=ec&&b<ac)}())return fc;if(function(){return a.indexOf(gc)!=-1||b>=ac}())return hc;return cb};k[Xb]={gecko1_8:W,ie10:X,ie8:Wb,ie9:ic,safari:jc};__gwt_isKnownPropertyValue=function(a,b){return b in k[a]};org_gwtbook_Dashboard.__getPropMap=function(){var a={};for(var b in k){if(k.hasOwnProperty(b)){a[b]=m(b)}}return a};org_gwtbook_Dashboard.__computePropValue=m;p.__gwt_activeModules[T].bindings=org_gwtbook_Dashboard.__getPropMap;s(P,kc);if(r()){return F(lc)}var n;try{alert(mc);return;var o=n.indexOf(nc);if(o!=-1){j=parseInt(n.substring(o+X),_b);n=n.substring(W,o)}}catch(a){}org_gwtbook_Dashboard.__softPermutationId=j;return F(n+oc)}
function H(){if(!p.__gwt_stylesLoaded){p.__gwt_stylesLoaded={}}function c(a){if(!__gwt_stylesLoaded[a]){var b=q.createElement(pc);b.setAttribute(qc,rc);b.setAttribute(sc,F(a));q.getElementsByTagName(qb)[W].appendChild(b);__gwt_stylesLoaded[a]=true}}
s(tc,Q);c(uc);s(tc,vc)}
C();org_gwtbook_Dashboard.__moduleBase=D();u[T].moduleBase=org_gwtbook_Dashboard.__moduleBase;var I=G();if(p){var J=!!(p.location.protocol==wc||p.location.protocol==xc);p.__gwt_activeModules[T].canRedirect=J;function K(){var b=yc;try{p.sessionStorage.setItem(b,b);p.sessionStorage.removeItem(b);return true}catch(a){return false}}
if(J&&K()){var L=zc;var M=p.sessionStorage[L];if(!/^http:\/\/(localhost|127\.0\.0\.1)(:\d+)?\/.*$/.test(M)){if(M&&(window.console&&console.log)){console.log(Ac+M)}M=cb}if(M&&!p[L]){p[L]=true;p[L+Bc]=D();var N=q.createElement(kb);N.src=M;var O=q.getElementsByTagName(qb)[W];O.insertBefore(N,O.firstElementChild||O.children[W]);return false}}}H();s(P,vc);B(I);return true}
org_gwtbook_Dashboard.succeeded=org_gwtbook_Dashboard();