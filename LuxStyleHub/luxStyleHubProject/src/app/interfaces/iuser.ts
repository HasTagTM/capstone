export interface IUser {
  accessToken:string,
  tokenType:string,
  username:string,
  roles:{id:number, roleName:string}[]
}
