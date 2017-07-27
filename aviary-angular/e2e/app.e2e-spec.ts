import { AviaryAngularPage } from './app.po';

describe('aviary-angular App', () => {
  let page: AviaryAngularPage;

  beforeEach(() => {
    page = new AviaryAngularPage();
  });

  it('should display welcome message', () => {
    page.navigateTo();
    expect(page.getParagraphText()).toEqual('Welcome to app!!');
  });
});
