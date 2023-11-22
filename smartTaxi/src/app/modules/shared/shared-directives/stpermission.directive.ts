import { Directive, ElementRef, Input, OnInit, Renderer2 } from '@angular/core';
import jwt_decode from 'jwt-decode';

@Directive({
  selector: '[appSTPermission]',
})
export class STPermissionDirective implements OnInit {
  @Input('screenName') screenName: string = '';

  constructor(private el: ElementRef, private renderer: Renderer2) {}

  ngOnInit(): void {
    this.checkAccess();
  }

  checkAccess() {
    let token = localStorage.getItem('token');
    if (token) {
      const decodedToken: any = jwt_decode(token);
      const id = this.el.nativeElement.id;
      if (id && decodedToken[this.screenName]) {
        const homeItem = decodedToken[this.screenName];
        const permissions = {
          canDelete: homeItem.canDelete,
          canAdd: homeItem.canAdd,
          canDisplay: homeItem.canDisplay,
        };

        if (id.startsWith('add')) {
          this.el.nativeElement.style.display = permissions.canAdd
            ? 'block'
            : 'none';
        } else if (id.startsWith('update')) {
          this.el.nativeElement.style.display = permissions.canDisplay
            ? 'block'
            : 'none';
        } else if (id.startsWith('delete')) {
          this.el.nativeElement.style.display = permissions.canDelete
            ? 'block'
            : 'none';
        } else {
        }

        // Check permissions and set display style
        // const addButton = this.renderer.getElementById('add-*');
        // addButton.style.display =permissions.canAdd ? "block" : "none";
      }
    }
  }
}
