import { ColumnDef } from "@tanstack/react-table";
import { Task } from "../data/schema_products";
import { DataTableColumnHeader } from "./data-table-column-header-users";
import { Button } from "@/components/ui/button";
import {
  AlertDialog,
  AlertDialogCancel,
  AlertDialogContent,
  AlertDialogDescription,
  AlertDialogFooter,
  AlertDialogHeader,
  AlertDialogTitle,
  AlertDialogTrigger,
} from "@/components/ui/alert-dialog"
import {
  Dialog,
  DialogContent,
  DialogDescription,
  DialogFooter,
  DialogHeader,
  DialogTitle,
  DialogTrigger,
} from "@/components/ui/dialog"


export const createColumns = (handleDelete: (id: number) => void): ColumnDef<Task>[] => [
    {
      accessorKey: "name",
      header: ({ column }) => (
        <DataTableColumnHeader column={column} title="Nombre" />
      ),
      cell: ({ row }) => {
        return (
          <div className="flex space-x-2">
            <span className="max-w-[500px] truncate font-medium">
              {row.getValue("name")}
            </span>
          </div>
        );
      },
    },
    {
      accessorKey: "stock",
      header: ({ column }) => (
        <DataTableColumnHeader column={column} title="Stock" />
      ),
      cell: ({ row }) => {
        return (
          <div className="flex space-x-2">
            <span className="max-w-[500px] truncate font-medium">
              {row.getValue("stock")}
            </span>
          </div>
        );
      },
    },
    {
      accessorKey: "price",
      header: ({ column }) => (
        <DataTableColumnHeader column={column} title="Precio" />
      ),
      cell: ({ row }) => {
        return (
          <div className="flex space-x-2">
            <span className="max-w-[500px] truncate font-medium">
              {row.getValue("price")}
            </span>
          </div>
        );
      },
    },
    {
      accessorKey: "model",
      header: ({ column }) => (
        <DataTableColumnHeader column={column} title="Model" />
      ),
      cell: ({ row }) => {
        return (
          <div className="flex space-x-2">
            <span className="max-w-[500px] truncate font-medium">
              {row.getValue("model")}
            </span>
          </div>
        );
      },
    },
    {
      accessorKey: "size",
      header: ({ column }) => (
        <DataTableColumnHeader column={column} title="Tamaño" />
      ),
      cell: ({ row }) => {
        return (
          <div className="flex space-x-2">
            <span className="max-w-[500px] truncate font-medium">
              {row.getValue("size")}
            </span>
          </div>
        );
      },
    },
    {
      accessorKey: "material",
      header: ({ column }) => (
        <DataTableColumnHeader column={column} title="Material" />
      ),
      cell: ({ row }) => {
        return (
          <div className="flex space-x-2">
            <span className="max-w-[500px] truncate font-medium">
              {row.getValue("material")}
            </span>
          </div>
        );
      },
    },
    {
      accessorKey: "absorbency",
      header: ({ column }) => (
        <DataTableColumnHeader column={column} title="Absorbencia" />
      ),
      cell: ({ row }) => {
        return (
          <div className="flex space-x-2">
            <span className="max-w-[500px] truncate font-medium">
              {row.getValue("absorbency")}
            </span>
          </div>
        );
      },
    },
    {
      accessorKey: "material_feature",
      header: ({ column }) => (
        <DataTableColumnHeader column={column} title="Cuidado de la Piel" />
      ),
      cell: ({ row }) => {
        return (
          <div className="flex space-x-2">
            <span className="max-w-[500px] truncate font-medium">
              {row.getValue("material_feature")}
            </span>
          </div>
        );
      },
    },
    {
      accessorKey: "color",
      header: ({ column }) => (
        <DataTableColumnHeader column={column} title="Color" />
      ),
      cell: ({ row }) => {
        return (
          <div className="flex space-x-2">
            <span className="max-w-[500px] truncate font-medium">
              {row.getValue("color")}
            </span>
          </div>
        );
      },
    },
    {
      accessorKey: "description",
      header: ({ column }) => (
        <DataTableColumnHeader column={column} title="Descripción" />
      ),
      cell: ({ row }) => {
        return (
          <div className="flex space-x-2">
            <span className="max-w-[500px] truncate font-medium">
              {row.getValue("description")}
            </span>
          </div>
        );
      },
    },
    {
      accessorKey: "imagen",
      header: ({ column }) => (
        <DataTableColumnHeader column={column} title="Imagen" />
      ),
      cell: ({ row }) => {
        return (
          <AlertDialog>
            <AlertDialogTrigger asChild>
              <Button variant="outline"
              >Ver Imagen</Button>
            </AlertDialogTrigger>
            <AlertDialogContent>
              <AlertDialogHeader>
                <AlertDialogTitle>Imagen</AlertDialogTitle>
                <AlertDialogDescription>
                  <img src={(row.original as any).url} alt="imagen" />
                </AlertDialogDescription>
              </AlertDialogHeader>
              <AlertDialogFooter>
                <AlertDialogCancel>Cerrar</AlertDialogCancel>
              </AlertDialogFooter>
            </AlertDialogContent>
          </AlertDialog>
        );
      },
    },
    {
      id: "actions",
      header: "Acciones",
      cell: ({ row }) => {
        return (
          <div className="flex justify-end">
            <Dialog>
              <DialogTrigger asChild>
                <Button variant="destructive">Eliminar</Button>
              </DialogTrigger>
              <DialogContent className="sm:max-w-[425px]">
                  <DialogHeader>
                    <DialogTitle>Eliminar Producto</DialogTitle>
                    <DialogDescription>
                      Esta acción no se puede deshacer.
                    </DialogDescription>
                  </DialogHeader>
                  <DialogFooter>
                  <Button 
                    variant="destructive" 
                    onClick={() => handleDelete((row.original as any).id)}
                  >
                    Eliminar
                  </Button>
                  </DialogFooter>
              </DialogContent>
            </Dialog>
          </div>
        );
      },
    },
  ];
  