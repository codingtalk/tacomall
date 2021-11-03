
export const fullScreen = () => {
  const el = document.documentElement;
  const rfs = el.requestFullScreen || el.webkitRequestFullScreen || el.mozRequestFullScreen || el.msRequestFullScreen;

  if (rfs) {
    rfs.call(el);
  } else if (typeof window.ActiveXObject !== 'undefined') {
    const wscript = new window.ActiveXObject('WScript.Shell');
    if (wscript != null) {
      wscript.SendKeys('{F11}');
    }
  }
};

export const exitFullScreen = () => {
  const el = document;
  const cfs = el.cancelFullScreen || el.webkitCancelFullScreen || el.mozCancelFullScreen || el.exitFullScreen;

  if (cfs) {
    cfs.call(el);
  } else if (typeof window.ActiveXObject !== 'undefined') {
    const wscript = new window.ActiveXObject('WScript.Shell');
    if (wscript != null) {
      wscript.SendKeys('{F11}');
    }
  }
};
