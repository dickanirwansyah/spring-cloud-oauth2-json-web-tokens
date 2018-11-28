import { NgModule, Injectable } from '@angular/core';
import { Router, RouterModule } from '@angular/router';
import { Cookie } from 'ng2-cookies';
import { Http, Response, Headers, RequestOptions } from '@angular/http';
import { map } from 'rxjs/operators';

@Injectable()
export class AuthService{

    AUTH_TOKEN_URI = '/oauth/token';

    constructor(
        public router: Router,
        public http: Http
    ){}

    getOauth2(loginData){
        let params = new URLSearchParams();
        params.append('username', loginData.username);
        params.append('password', loginData.password);
        params.append('grant_type', 'password');
        params.append('client_id', 'USER_CLIENT_APP');

        let headers = new Headers({'Content-Type': 'application/x-www-form-urlencoded; charset=utf-8', 'Authorization': 'Basic '+btoa("USER_CLIENT_APP:password")});

        let options = new RequestOptions({headers: headers});
        console.log(params.toString());

        /** post login oauth/token */
        this.http.post('http://localhost:9001/oauth/token', params.toString(), options)
            .pipe(map(res => res.json()))
                .subscribe(
                    dataInCookies => this.saveTokenInCookies(dataInCookies),
                    error => alert('login failed '+error)
                )
    }

    decodeJwtToken(){
       //var base64Url = token.split('.')[1];
        //var base64 = base64Url.replace('-', '+').replace('_', '/')
        //return JSON.parse(window.atob(base64));
        //let jwtData1 = jwtData.replace('-', '+').replace('_', '/')
        let jwt = localStorage.getItem('access_token');
        let jwtData = jwt.split('.')[1]
        let decodedJwtJsonData = window.atob(jwtData)
        let decodedJwtData = JSON.parse(decodedJwtJsonData)

        let isAdmin = decodedJwtData.authorities.role_admin

        console.log('jwtData = '+jwtData)
        console.log('decodedJwtJsonData = '+decodedJwtJsonData)
        console.log('decodedJwtData = '+decodedJwtData)
        console.log('Is admin : ' + isAdmin);
    }

    /**isAuthenticated 
    isAuthenticated(): boolean{
        const token = localStorage.getItem('access_token');
        //return !this.jwtHelper.isTokenExpired(token);
    }
    **/

    /**decode jwt 
    let jwt = 'eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJzdWIiOiIxMjM0NTY3ODkwIiwibmFtZSI6IkpvaG4gRG9lIiwiYWRtaW4iOnRydWV9.TJVA95OrM7E2cBab30RMHrHDcEfxjoYZgeFONFh7HgQ'

    let jwtData = jwt.split('.')[1]
    let decodedJwtJsonData = window.atob(jwtData)
    let decodedJwtData = JSON.parse(decodedJwtJsonData)

    let isAdmin = decodedJwtData.admin

    console.log('jwtData: ' + jwtData)
    console.log('decodedJwtJsonData: ' + decodedJwtJsonData)
    console.log('decodedJwtData: ' + decodedJwtData)
    console.log('Is admin: ' + isAdmin)
    decode jwt */

    /** save token in cookies */
    saveTokenInCookies(token){
        var expireDateToken = new Date()
            .getTime() + (1000 * token.expires_in);
        Cookie.set('access_token', token.access_token, expireDateToken);
        /**save token in localstorage */
        localStorage.setItem('access_token', token.access_token);
        console.log('processing mendapatkan token : '+token.toString());
        this.router.navigate(['/']);
    }

    /** save token in localstorage */
    

    checkTokenInCookies(){
        if(!Cookie.check('access_token')){
            this.router.navigate(['/auth-login']);
        }
    }

    /** logout hapus cookies */
    logout(){
        Cookie.delete('access_token');
        window.location.reload();
        console.log('token berhasil dihapus dari cookies.')
    }
}