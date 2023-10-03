
import { Iprodotto } from "./iprodotto";

export interface Page {
  content:Iprodotto[],
  empty:boolean,
  first:boolean,
  last:boolean,
  number:number,
  numberOfElements:number,
  pageable:{offset:number, pageNumber:number, pageSize:number, paged:boolean}[]
  size:number,
  sort:{empty:boolean, sorted:boolean, unsorted:boolean}[]
  totalElements:number,
  totalPages:number
}
