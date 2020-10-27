import {Card} from "./card.model";

export class Poa {
  id: string;
  grantor: string;
  grantee: string;
  account: string;
  direction: string;
  authorizations: Array<String>;
  cards: Array<Card>;
}
