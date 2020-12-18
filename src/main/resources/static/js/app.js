const appEl = document.getElementById('app');
const baseUrl = 'http://localhost:8080';

async function hashHandler() {
  console.log('The hash has changed!', location.hash);
  const hash = !location.hash ? '#/' : location.hash;
  appEl.innerHTML = await routes[hash]();
}

async function init() {
  const hash = !location.hash ? '#/' : location.hash;
  appEl.innerHTML = await routes[hash]();
}

function buildTemplate(tmpId, context) {
  var template = $('#' + tmpId).html();

  // Compile the template data into a function
  var templateScript = Handlebars.compile(template);
  var html = templateScript(context);
  return html;
}

const routes = {
  '#/': async() => {
    const res = await fetch(`${baseUrl}/v1/buckets`)
    const json = await res.json();
    return buildTemplate('buckets-list', { buckets: json })
  }
}

init();

window.addEventListener('hashchange', hashHandler, false);