import { useNavigate } from "react-router-dom";
import { cn } from "@/lib/utils";
import { Button } from "@/components/ui/button";
import { Input } from "@/components/ui/input";
import { Label } from "@/components/ui/label";
import { useState } from "react";
import { useAuth } from "@/hooks/useAuth";

export default function Home() {
  const navigate = useNavigate();
  const { signIn } = useAuth();
  const [email, setEmail] = useState("");
  const [password, setPassword] = useState("");
  const [error] = useState("");

  function handleLogin(event: React.FormEvent<HTMLFormElement>) {
    event.preventDefault(); // Prevenir la recarga
    signIn(email, password, navigate);
  }


  return (
    <div className="container h-full min-h-screen items-center justify-center grid lg:max-w-none lg:grid-cols-2 lg:px-0">
      <div className="relative hidden h-full flex-col bg-muted p-10 text-white dark:border-r lg:flex">
        <div className="absolute inset-0 bg-t-background" />
        <div className="relative z-20 flex items-center text-lg font-medium">
          Todas Brillamos
        </div>
        <div className="relative z-20 mt-auto">
          <blockquote className="space-y-2">
            <p className="text-lg">
              &ldquo;Nosotras queremos que todas las mujeres brillen, que todas
              las mujeres sean&rdquo;
            </p>
            <footer className="text-sm">Todas Brillamos</footer>
          </blockquote>
        </div>
      </div>
      <div className="lg:p-8">
        <div className="mx-auto flex w-full flex-col justify-center space-y-6 sm:w-[350px]">
          <div className="flex flex-col space-y-2 text-center">
            <h1 className="text-2xl font-semibold tracking-tight">
              Entra a tu cuenta
            </h1>
            <p className="text-sm text-muted-foreground">
              Introduce tu usuario y contraseña para iniciar sesión
            </p>
          </div>
          {error && <p className="text-red-500 text-center">{error}</p>}{" "}
          {/* Mostrar errores */}
          <div className={cn("grid gap-6")}>
            <form onSubmit={handleLogin}>
              {" "}
              {/* Manejar envío de formulario */}
              <div className="grid gap-2">
                <div className="grid gap-1">
                  <Label className="card" htmlFor="email">
                    Email
                  </Label>
                  <div className="flex flex-col gap-5 pb-5">
                    <Input
                      id="email"
                      placeholder="example@todasbrillamos.mx"
                      type="email"
                      autoCapitalize="none"
                      autoComplete="email"
                      autoCorrect="off"
                      value={email}
                      onChange={(e) => setEmail(e.target.value)}
                      // Manejar cambios en email
                    />
                    <Input
                      id="password" // Cambiar id a password
                      type="password"
                      autoCapitalize="none"
                      autoComplete="current-password" // Cambiar a current-password
                      autoCorrect="off"
                      value={password}
                      onChange={(e) => setPassword(e.target.value)} // Manejar cambios en password
                    />
                  </div>
                </div>
                <Button type="submit">Continue</Button>
              </div>
            </form>
          </div>
          
        </div>
      </div>
    </div>
  );
}
