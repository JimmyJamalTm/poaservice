import {AtmLimit} from "./atmlimit.model";
import {PosLimit} from "./poslimit.model";

export class Debitcard {
  id: string;
  status: string;
  cardNumber: number;
  sequenceNumber: number;
  cardHolder: string;
  atmLimit: AtmLimit;
  posLimit: PosLimit;
  contactless; boolean;
}
