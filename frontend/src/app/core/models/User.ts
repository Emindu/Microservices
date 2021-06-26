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

export function instanceOfUserDto(user: any): user is User {
  if (!user) { return false; }
  for (const el of ['id', 'username', 'fullName', 'reputation']) {
    if ( !(el in user) ) { return false; }
  }
  return  true;
}
