export interface UserDetailsResponse{
  id: string;
  firstname: string;
  lastname: string;
  email: string;
  address: string;
  phone: string;
  birthdate: Date;
  photoImage: string;

  // description?: Description;

  description: string;
  skills: string;
  experience: string;
}
