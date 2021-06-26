export interface User {
  id: string | number;
  username: string;
  fullName: string;
  about?: string;
  dateAdded?: Date;
  dataUpdated?: Date;
  country?: string;
  reputation: number;
}
