import React from 'react';
import MenuItem from 'app/shared/layout/menus/menu-item';
import { DropdownItem } from 'reactstrap';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { NavLink as Link } from 'react-router-dom';
import { NavDropdown } from './menu-components';

export const EntitiesMenu = props => (
  <NavDropdown icon="th-list" name="Entities" id="entity-menu">
    <MenuItem icon="asterisk" to="/categoria">
      Categoria
    </MenuItem>
    <MenuItem icon="asterisk" to="/documento">
      Documento
    </MenuItem>
    <MenuItem icon="asterisk" to="/grupo-solicitante">
      Grupo Solicitante
    </MenuItem>
    <MenuItem icon="asterisk" to="/numeracao">
      Numeracao
    </MenuItem>
    <MenuItem icon="asterisk" to="/setor">
      Setor
    </MenuItem>
    <MenuItem icon="asterisk" to="/tipo-protocolo">
      Tipo Protocolo
    </MenuItem>
    <MenuItem icon="asterisk" to="/tipo-usuario">
      Tipo Usuario
    </MenuItem>
    <MenuItem icon="asterisk" to="/usuario">
      Usuario
    </MenuItem>
    <MenuItem icon="asterisk" to="/versao">
      Versao
    </MenuItem>
    <MenuItem icon="asterisk" to="/protocolo">
      Protocolo
    </MenuItem>
    {/* jhipster-needle-add-entity-to-menu - JHipster will add entities to the menu here */}
  </NavDropdown>
);
